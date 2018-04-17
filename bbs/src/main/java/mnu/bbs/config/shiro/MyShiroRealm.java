/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.config.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import mnu.bbs.common.utils.StringUtil;
import mnu.bbs.domain.entity.Permission;
import mnu.bbs.domain.entity.User;
import mnu.bbs.domain.mapper.PermissionMapper;
import mnu.bbs.domain.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xian
 * date 2018/4/12 14:35
 * desc 自定义Shiro 认证和授权
 */
public class MyShiroRealm extends AuthorizingRealm {
	
	private static final transient Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo (AuthenticationToken authenticationToken) throws AuthenticationException {
		logger.info("认证中... ...");
		//获取用户输入的账号
		String username = (String) authenticationToken.getPrincipal();
		logger.info("username={}",username);
		//查询该用户信息
		//查询用户信息
		EntityWrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq("name", username).or().eq("email",username).or().eq("number",username);
		User user = iUserService.selectOne(wrapper);
		if (user == null) {
			System.out.println("user=null");
			return null;
		}
		logger.info("status={}",user.getStatus());
		if(user.getStatus() == 0) {
			throw new LockedAccountException(); //帐号锁定
		}
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()),"MyShiroRealm");
		
		return simpleAuthenticationInfo;
	}
	
	/**
	 *权限信息.(授权)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo (PrincipalCollection principalCollection) {
		logger.info("授权中... ...");
		// 获取用户信息
		User user = (User) principalCollection.getPrimaryPrincipal();
		logger.info("user={}",user);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		// 查询用户拥有那些权限
		List<Permission> permissions = permissionMapper.findUserPermission(user.getId());
		List<String> list = new ArrayList<String>();
		// 添加权限代码
		for (Permission item: permissions) {
			logger.info("perCoe={}",item);
			if (StringUtil.isNotEmpty(item.getPermCode())) {
				list.add(item.getPermCode());
			}
		}
		authorizationInfo.addStringPermissions(list);
		return authorizationInfo;
	}
}
