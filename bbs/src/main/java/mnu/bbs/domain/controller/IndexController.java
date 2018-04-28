/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import mnu.bbs.common.controller.BaseController;
import mnu.bbs.common.result.JsonResult;
import mnu.bbs.common.utils.AddressUtil;
import mnu.bbs.common.utils.CodeUtil;
import mnu.bbs.common.utils.DateUtil;
import mnu.bbs.common.utils.StringUtil;
import mnu.bbs.domain.dto.UserPostDto;
import mnu.bbs.domain.entity.Post;
import mnu.bbs.domain.entity.User;
import mnu.bbs.domain.service.IPermissionService;
import mnu.bbs.domain.service.IPostService;
import mnu.bbs.domain.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
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
import java.util.HashMap;
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
	
	@Autowired
	private IPostService postService;
	
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
		//最新发布的贴子
		List<UserPostDto> posts = postService.selectNewPost(2);
		
		model.addAttribute("posts",posts);
		model.addAttribute("user",user);
		model.addAttribute("week",week);
		return "/index";
	}
	
	/**
	 * 用户注册界面
	 */
	@GetMapping(value = "/register")
	public String registerView() {
		return "/register";
	}
	
	/**
	 * 用户注册
	 */
	@ResponseBody
	@PostMapping(value = "/register")
	public JsonResult register(User user, String code) {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String sessionCode = (String) session.getAttribute("code");
		session.removeAttribute("code");
		if (sessionCode == null) {
			return renderSuccess("验证码过期请重新获取");
		}
		if (!sessionCode.equalsIgnoreCase(code)) {
			return renderError("验证码错误");
		}
		user.setStatus("正常"); // 状态
		// 创建盐, 散列加密
		String salt = String.valueOf(System.currentTimeMillis());
		SimpleHash password = new SimpleHash("MD5", user.getPassword(), salt);
		user.setSalt(salt); // 设置盐
		user.setPassword(password.toString()); // 设置密码
		user.setName(user.getName()); // 设置昵称
		user.setNumber(user.getNumber());
		return iUserService.insert(user) ? renderSuccess("注册成功") : renderSuccess("注册失败");
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
	 * 板块控制：综合讨论，二手交易，谈天说地，摄影天地，资源分享
	 */
	@GetMapping("/forum")
	public String forum(@RequestParam(name = "type",required = false) String type,Model model,@RequestParam(name = "pageNumber",defaultValue = "1") Integer pageNumber,
	                    @RequestParam(name = "sort",required = false) String sort) {
		int pageSize = 10;
		System.out.println("type: " + type);
		System.out.println("pageNumber: " + pageNumber);
		System.out.println("sort: " + sort);
		Page<UserPostDto> page = new Page<>(pageNumber, pageSize);
		Page<UserPostDto> postPage = postService.selectUserPostPage(page,type,sort);
		//int 对应板块的总记录数
		model.addAttribute("total", postPage.getTotal());
		System.out.println("total: " + postPage.getTotal());
		//List<Post> 对应页数的记录集合
		model.addAttribute("rows", postPage.getRecords());
		model.addAttribute("type",type);
		model.addAttribute("user",getCurrentUser());
		
		return "/forum";
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
	
	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}
}
