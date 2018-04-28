/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import mnu.bbs.common.controller.BaseController;
import mnu.bbs.common.result.JsonResult;
import mnu.bbs.common.utils.AddressUtil;
import mnu.bbs.common.utils.CodeUtil;
import mnu.bbs.domain.dto.UserActivityDto;
import mnu.bbs.domain.entity.Post;
import mnu.bbs.domain.entity.User;
import mnu.bbs.domain.service.IPostService;
import mnu.bbs.domain.service.IRoleService;
import mnu.bbs.domain.service.IUserService;
import org.apache.shiro.SecurityUtils;
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
import java.util.List;

/**
 * @author xian
 * date 2018/4/13 13:29
 * desc user 用户表 前端控制器
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController {
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private IRoleService iRoleService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private IPostService postService;
	
	@Value("${spring.mail.username}")
	private String mailUsername;
	
	/**
	 * 添加用户
	 * param user
	 * return JsonResult [true, 200, msg, null]
	 */
	@ResponseBody
	@RequestMapping(value = "/addUser")
	public JsonResult addUser(User user) {
		// 创建盐, 散列加密
		String salt = String.valueOf(System.currentTimeMillis());
		SimpleHash password = new SimpleHash("MD5", user.getPassword(), salt);
		user.setSalt(salt);
		user.setPassword(password.toString());
		return  iUserService.insert(user) ? renderSuccess("添加成功") : renderError("添加失败");
	}
	
	@ResponseBody
	@PostMapping("/updateUser")
	public JsonResult updateUser(Model model, @RequestBody String param, HttpServletRequest request) {
		Integer id = getCurrentLoginId();
		System.out.println("param:" + param);
		int index = param.lastIndexOf("=") + 1;
		String value = param.substring(index);
		System.out.println("value: " + value);
		String flied = param.substring(0,index - 1);
		System.out.println("flied:" + flied);
		switch (flied) {
			case "name" : return iUserService.updateName(id,value) ? renderSuccess("更改成功") : renderError("更改失败");
			case "email" : {
				//验证邮箱
				String ip = AddressUtil.getIp(request);
				String userName = getCurrentLoginUsername();
				value = value.replace("%40", "@");// 字符串替换;
				String msg = iUserService.selectEmail(getCurrentLoginId(),value);
				if (!msg.equals("邮箱正确")) return renderError(msg);
				//int code = (int)((Math.random()*9+1)*100000);
				String code = CodeUtil.getCode();
				Subject subject = SecurityUtils.getSubject();
				Session session = subject.getSession();
				session.setAttribute("code", code);

        /*SimpleMailMessage message = new SimpleMailMessage();*/
				MimeMessage mimeMessage = mailSender.createMimeMessage();
				try {
					// 指明邮件的发件人
					mimeMessage.setFrom(new InternetAddress(mailUsername));
					//指明邮件的收件人
					mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(value));
					//邮件的标题
					mimeMessage.setSubject("修改密码验证");
					//邮件的文本内容
					mimeMessage.setContent("<html>\n" +
							"<body>\n" +
							"    <table width=\"470\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px; padding-bottom: 10px;\">\n" +
							"\n" +
							"        <tbody>\n" +
							"        <tr bgcolor=\"#17212e\">\n" +
							"            <td style=\"padding-top: 32px;\">\n" +
							"                <span style=\"padding-top: 16px; padding-bottom: 16px; font-size: 24px; color: #66c0f4; font-family: Arial, Helvetica, sans-serif; font-weight: bold;\">\n" +
							"                    亲爱的 "+userName+",\n" +
							"                </span><br>\n" +
							"            </td>\n" +
							"        </tr>\n" +
							"        <tr bgcolor=\"#17212e\">\n" +
							"            <td style=\"padding-top: 12px;\">\n" +
							"                <span style=\"font-size: 17px; color: #c6d4df; font-family: Arial, Helvetica, sans-serif; font-weight: bold;\">\n" +
							"                    <p>这是您修改账号"+userName+"密码的验证码：</p>\n" +
							"                </span>\n" +
							"            </td>\n" +
							"        </tr>\n" +
							"\n" +
							"        <tr bgcolor=\"#17212e\">\n" +
							"            <td>\n" +
							"                <div>\n" +
							"                    <span style=\"font-size: 24px; color: #66c0f4; font-family: Arial, Helvetica, sans-serif; font-weight: bold;\">"+code+"</span>\n" +
							"                </div>\n" +
							"            </td>\n" +
							"        </tr>\n" +
							"\n" +
							"        <tr bgcolor=\"#121a25\">\n" +
							"            <td style=\"padding: 20px; font-size: 12px; line-height: 17px; color: #c6d4df; font-family: Arial, Helvetica, sans-serif;\">\n" +
							"                <p style=\"padding-bottom: 10px; color: #c6d4df;\">您之所以会收到此邮件， 位于 "+ip+"正在尝试修改密码！</p>\n" +
							"                <p style=\"padding-bottom: 10px; color: #c6d4df;\"><span style=\"color: #ffffff; font-weight: bold;\">如果您没有登录此账号</span>请尽快修改密码，以确保账号安全。</p>\n" +
							"            </td>\n" +
							"        </tr>\n" +
							"\n" +
							"        </tbody>\n" +
							"    </table>\n" +
							"</body>\n" +
							"</html>", "text/html;charset=UTF-8");
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				try {
					mailSender.send(mimeMessage); //发送邮件
					return renderSuccess("请查看邮箱验证码");
				} catch (MailException e) {
					System.out.println(e.getMessage());
					return renderError("邮箱发送失败,请重新获取");
				}
			}
			case "password" :
			{
				if(iUserService.updatePassword(id,value)) {
					return 	renderSuccess("更改成功,请重新登录");
				}
				return renderError("更改失败");
			}
			default: return renderError("ERROR");
		}
	}
	/**
	 * 邮箱发送验证码
	 * param mail
	 */
	@ResponseBody
	@PostMapping("/sendCode")
	public JsonResult sendCode(@RequestBody String mail,HttpServletRequest request) {
		
		mail = mail.replace("%40", "@");// 字符串替换
		
		// 判断是否已经注册
		EntityWrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq("email", mail);
		User user = iUserService.selectOne(wrapper);
		if (user != null) {
			return renderError("邮箱已注册");
		}
		String ip = AddressUtil.getIp(request);
		String code = CodeUtil.getCode();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.setAttribute("code", code);

        /*SimpleMailMessage message = new SimpleMailMessage();*/
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		try {
			// 指明邮件的发件人
			mimeMessage.setFrom(new InternetAddress(mailUsername));
			//指明邮件的收件人
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
			//邮件的标题
			mimeMessage.setSubject("MNUF-注册验证");
			//邮件的文本内容
			mimeMessage.setContent("<html>\n" +
					"<body>\n" +
					"    <table width=\"470\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px; padding-bottom: 10px;\">\n" +
					"\n" +
					"        <tbody>\n" +
					"        <tr bgcolor=\"#17212e\">\n" +
					"            <td style=\"padding-top: 32px;\">\n" +
					"                <span style=\"padding-top: 16px; padding-bottom: 16px; font-size: 24px; color: #66c0f4; font-family: Arial, Helvetica, sans-serif; font-weight: bold;\">\n" +
					"                    欢迎注册MNUF,\n" +
					"                </span><br>\n" +
					"            </td>\n" +
					"        </tr>\n" +
					"        <tr bgcolor=\"#17212e\">\n" +
					"            <td style=\"padding-top: 12px;\">\n" +
					"                <span style=\"font-size: 17px; color: #c6d4df; font-family: Arial, Helvetica, sans-serif; font-weight: bold;\">\n" +
					"                    <p>这是您注册账号的验证码：</p>\n" +
					"                </span>\n" +
					"            </td>\n" +
					"        </tr>\n" +
					"\n" +
					"        <tr bgcolor=\"#17212e\">\n" +
					"            <td>\n" +
					"                <div>\n" +
					"                    <span style=\"font-size: 24px; color: #66c0f4; font-family: Arial, Helvetica, sans-serif; font-weight: bold;\">"+code+"</span>\n" +
					"                </div>\n" +
					"            </td>\n" +
					"        </tr>\n" +
					"\n" +
					"        <tr bgcolor=\"#121a25\">\n" +
					"            <td style=\"padding: 20px; font-size: 12px; line-height: 17px; color: #c6d4df; font-family: Arial, Helvetica, sans-serif;\">\n" +
					"                <p style=\"padding-bottom: 10px; color: #c6d4df;\">您之所以会收到此邮件， 位于 "+ip+"正在尝试使用该邮箱注册！</p>\n" +
					"                <p style=\"padding-bottom: 10px; color: #c6d4df;\"><span style=\"color: #ffffff; font-weight: bold;\">如果您没有注册</span>请不要回复此邮件。</p>\n" +
					"            </td>\n" +
					"        </tr>\n" +
					"\n" +
					"        </tbody>\n" +
					"    </table>\n" +
					"</body>\n" +
					"</html>", "text/html;charset=UTF-8");
		} catch (MessagingException e) {
			e.printStackTrace();
		}


        /*message.setFrom("fanshuye1304@163.com");//发送者.
        message.setTo(mail); // 接收者.
        message.setSubject("Bing-Upms 注册验证"); // 邮件主题.
        message.setText("<h1>验证码</h1>"); // 邮件内容.*/
		
		try {
			mailSender.send(mimeMessage); //发送邮件
			return renderSuccess("请查看邮箱验证码");
		} catch (MailException e) {
			System.out.println(e.getMessage());
			return renderError("邮箱发送失败,请重新获取");
		}
	}
	/**
	* Author: xian
	* Date 2018/4/21 11:25
	* Description  查询今日注册的用户
	*/
	@PostMapping("/selectTodayUser")
	@ResponseBody
	public List<User> selectTodayUser() {
		List<User> users = iUserService.selectTodayUser();
		return users;
	}
	
	/**
	* Author: xian
	* Date 2018/4/21 11:24
	* Description 查询指定用户ID的活动（发帖记录）
	*/
	@GetMapping("/user-profile/{id}")
	public String selectUserActivity(@PathVariable("id") Integer id,Model model) {
		User otherUser = iUserService.selectById(id);
		List<Post> posts = postService.selectPostByUserId(id);
		if (otherUser == null) return "redirect:/404";
		model.addAttribute("otherUser",otherUser);
		model.addAttribute("posts",posts);
		return "/user-profile";
	}
	
	@GetMapping("/user-detail")
	public String selectAllUser(Model model) {
		EntityWrapper<User> ew = new EntityWrapper<User>();
		ew.where("1",1);
		List<User> users = iUserService.selectList(ew);
		model.addAttribute("users",users);
		return "/user-detail";
	}
}
