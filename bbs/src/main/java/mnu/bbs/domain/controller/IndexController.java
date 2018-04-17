/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import mnu.bbs.common.controller.BaseController;
import mnu.bbs.common.result.JsonResult;
import mnu.bbs.common.utils.AddressUtil;
import mnu.bbs.common.utils.CodeUtil;
import mnu.bbs.common.utils.DateUtil;
import mnu.bbs.domain.entity.User;
import mnu.bbs.domain.service.IPermissionService;
import mnu.bbs.domain.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author xian
 * date 2018/4/13 13:34
 * desc
 */
@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private IPermissionService iPermissionService;
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//发送验证码的邮件账号
	@Value("${spring.mail.username}")
	private String mailUsername;
	
	// 主页
	@RequestMapping(value = {"/index","/"})
	public String index(Model model) {
		// 获取当前用户菜单
		/*List<Menu> menus = iPermissionService.createMenu(getCurrentLoginId());
		model.addAttribute("menus",menus);*/
		
		// 当前登录用户
		User user = getCurrentUser();
		//星期几
		String week = DateUtil.getWeek();
		
		model.addAttribute("user",user);
		model.addAttribute("week",week);
		return "/index";
	}
	
	/**
	 * 登陆页面
	 */
	@GetMapping("/login")
	public String loginForm() {
		return "/login";
	}
	
	/**
	 * 登陆提交验证页面
	 * param username
	 * param password
	 * param map
	 * return
	 */
	@RequestMapping("/mylogin")
	public String mylogin(String username, String password, Map<String, Object> map) {
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		if (!subject.isAuthenticated()) {
			try {
				subject.login(token);
			} catch ( UnknownAccountException uae ) {
				//username wasn't in the system, show them an error message?
				token.clear();
				map.put("msg", "用户不存在");
				return "/login";
			} catch ( IncorrectCredentialsException ice ) {
				//password didn't match, try again?
				token.clear();
				map.put("msg", "密码不正确");
				return "/login";
			} catch ( LockedAccountException lae ) {
				//account for that username is locked - can't login.  Show them a message?
				token.clear();
				map.put("msg","用户禁止登录");
				return "/login";
			} catch ( AuthenticationException ae ) {
				//unexpected condition - error?
				token.clear();
				map.put("msg","未知错误");
				return "/login";
			}
		}
		//记住我
		//token.setRememberMe(true);
		return "redirect:/index";
	}
	
	@GetMapping("/detail")
	public String detail(Model model) {
		model.addAttribute("user",getUpdatedUser());
		return "/detail";
	}
	/**
	 * 404页面
	 */
	@GetMapping("/404")
	public String notFound() {
		return "/404";
	}
	
	@GetMapping("/500")
	public String error () {
		return "/500";
	}
}
