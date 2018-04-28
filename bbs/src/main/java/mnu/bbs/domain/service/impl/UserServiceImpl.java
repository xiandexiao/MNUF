/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import mnu.bbs.domain.dto.UserActivityDto;
import mnu.bbs.domain.entity.User;
import mnu.bbs.domain.mapper.UserMapper;
import mnu.bbs.domain.service.IUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xian
 * date 2018/4/12 14:23
 * desc
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	@Transactional
	public boolean updateAvatar (String fileName,Integer id) {
		return userMapper.updateAvatar(fileName,id) > 0;
	}
	
	@Override
	public boolean updateName (Integer id, String name) {
		return userMapper.updateName(id, name) > 0;
	}
	
	@Override
	public String selectEmail (Integer id, String value) {
		String email = userMapper.selectEmail(id);
		System.out.println("service email:" + email);
		if (email == null) return "未绑定邮箱，请先绑定";
		else if(!email.equals(value)) return "与注册邮箱不一致";
		return  "邮箱正确";
	}
	
	@Override
	public boolean updatePassword (Integer id, String value) {
		String salt = String.valueOf(System.currentTimeMillis());
		System.out.println("service" + value);
		SimpleHash hash = new SimpleHash("MD5",value,salt,1);
		String password = hash.toString();
		return userMapper.updatePassword(id,password,salt) > 0;
	}
	
	@Override
	public List<User> selectTodayUser () {
		return userMapper.selectTodayUser();
	}
	
	@Override
	public List<UserActivityDto> selectUserActivity (Integer id) {
		return userMapper.selectUserActivity(id);
	}
}
