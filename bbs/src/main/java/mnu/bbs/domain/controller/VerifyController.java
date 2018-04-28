/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import mnu.bbs.common.controller.BaseController;
import mnu.bbs.common.result.JsonResult;
import mnu.bbs.domain.dto.UserPostDto;
import mnu.bbs.domain.entity.Comment;
import mnu.bbs.domain.entity.Post;
import mnu.bbs.domain.entity.User;
import mnu.bbs.domain.service.ICommentService;
import mnu.bbs.domain.service.IPostService;
import mnu.bbs.domain.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xian
 * date 2018/4/17 11:22
 * desc
 */
@RestController
@Controller
public class VerifyController extends BaseController{
	
	@Autowired
	private IPostService postService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICommentService commentService;
	
	@PostMapping("/verifyCode")
	public JsonResult verifyCode(@RequestBody String code) {
		code = code.substring(code.lastIndexOf("=") + 1);
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String sessionCode = (String)session.getAttribute("code");
		if (code.equalsIgnoreCase(sessionCode)) {
			session.removeAttribute("code");
			return renderSuccess("验证成功");
		}
		return renderError("验证码错误");
	}
	
	@PostMapping("/hotPost")
	public List<UserPostDto> hotPost() {
		//热门贴子信息 10条记录
		List<UserPostDto> hotPosts = postService.selectHotPosts(10);
		return hotPosts;
	}
	
	@PostMapping("/lockUser")
	public String lockUser(@RequestBody User user) {
		if ("正常".equals(user.getStatus())) {
			user.setStatus("被锁");
		}else {
			user.setStatus("正常");
		}
		return userService.updateById(user) ? "更改成功" : "更改失败";
	}
	
	@PostMapping("/deleteUser")
	public String deleteUser(@RequestBody User user) {
		String name = user.getName();
		return userService.deleteById(user.getId()) ? "删除"+name+"成功" : "删除"+name+"失败";
	}
	
	/**
	 * 删除指定ID的贴子，需先删除含外键的评论表，在删除此贴子
	 */
	@PostMapping("/deletePost")
	public String deletePost(@RequestBody Post post) {
		String title = post.getTitle();
		Integer id = post.getId();
		commentService.delete(new EntityWrapper<Comment>().eq("postId",id));
		return postService.deleteById(id) ? "删除"+title+"成功" : "删除"+title+"失败";
	}
	
	/**
	 * 判断用户名是否存在
	 * param username
	 */
	@RequestMapping("/isUsername")
	public Map<String, Object> isUsername(String username) {
		Map<String, Object> map = new HashMap<>();
		
		EntityWrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq("name", username);
		
		User user = userService.selectOne(wrapper);
		
		if (user == null) {
			map.put("valid", true);
		} else {
			map.put("valid", false);
		}
		return map;
	}
	/**
	 * 判断学号是否存在
	 * param username
	 */
	@RequestMapping("/isNumber")
	public Map<String, Object> isNumber(String number) {
		Map<String, Object> map = new HashMap<>();
		
		EntityWrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq("number", number);
		
		User user = userService.selectOne(wrapper);
		
		if (user == null) {
			map.put("valid", true);
		} else {
			map.put("valid", false);
		}
		return map;
	}
	/**
	 * 判断邮箱是否存在
	 * param email
	 */
	@RequestMapping("/isEmail")
	public Map<String, Object> isEmail(String email) {
		Map<String, Object> map = new HashMap<>();
		
		EntityWrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq("email", email);
		
		User user = userService.selectOne(wrapper);
		
		if (user == null) {
			map.put("valid", true);
		} else {
			map.put("valid", false);
		}
		return map;
	}
	
	
}
