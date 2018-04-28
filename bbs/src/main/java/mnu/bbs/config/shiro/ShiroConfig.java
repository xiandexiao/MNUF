/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xian
 * date 2018/4/12 14:49
 * desc
 */
@Configuration
public class ShiroConfig {
	private static final transient Logger log = LoggerFactory.getLogger(ShiroConfig.class);
	
	/**
	 *  Shiro 过滤器
	 * param securityManager
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		log.info("Shiro过滤器配置中... ...");
		ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
		
		// SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		// 登陆页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		// 拦截器.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		
		// 过滤链
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/assets/**", "anon");
		filterChainDefinitionMap.put("/avatar/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/particles.json", "anon");
		filterChainDefinitionMap.put("/500.html", "anon");
		filterChainDefinitionMap.put("/404.html", "anon");
		filterChainDefinitionMap.put("/mylogin", "anon");
		filterChainDefinitionMap.put("/register.html", "anon"); // 注册界面
		filterChainDefinitionMap.put("/register", "anon"); // 注册提交数据
		filterChainDefinitionMap.put("/sendCode", "anon"); // 发送邮箱验证码
		filterChainDefinitionMap.put("/isUsername", "anon"); // 判断用户名是否存在
		filterChainDefinitionMap.put("/isEmail", "anon"); // 判断邮箱是否存在
		filterChainDefinitionMap.put("/isNumber", "anon"); // 判断学号是否存在
		
		filterChainDefinitionMap.put("/**", "authc");
		/**
		 * anon:所有url都都可以匿名访问;
		 * authc: 需要认证才能进行访问;
		 * user:配置记住我或认证通过可以访问；
		 */
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
	/**
	 *  Shiro 安全管理器
	 */
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		
		//设置realm
		securityManager.setRealm(myShiroRealm());
		securityManager.setCacheManager(ehCacheManager());
		return securityManager;
	}
	
	/**
	 * 身份认证realm
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}
	
	/**
	 * 凭证匹配器
	 * 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 *  所以我们需要修改下doGetAuthenticationInfo中的代码;
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		
		hashedCredentialsMatcher.setHashAlgorithmName("MD5"); // 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(1); // 散列的次数，比如散列两次，相当于 md5(md5(""));
		
		return hashedCredentialsMatcher;
	}
	
	/**
	 * 配置Shiro 缓存
	 */
	@Bean
	public EhCacheManager ehCacheManager(){
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
		return cacheManager;
	}
	
	/**
	 * Shiro生命周期处理器 *
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	/**
	 *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全
	 *  逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 */
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}
	
	/**
	 * shiro注解支持
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
