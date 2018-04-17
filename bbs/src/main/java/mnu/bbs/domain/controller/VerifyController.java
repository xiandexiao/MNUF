/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.controller;

import mnu.bbs.common.controller.BaseController;
import mnu.bbs.common.result.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xian
 * date 2018/4/17 11:22
 * desc
 */
@RestController
@Controller
public class VerifyController extends BaseController{
	
	@PostMapping("/verifyCode")
	public JsonResult verifyCode(@RequestBody String code) {
		code = code.substring(code.lastIndexOf("=") + 1);
		System.out.println("code:" + code);
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String sessionCode = (String)session.getAttribute("code");
		System.out.println("sessionCode: " + sessionCode);
		System.out.println(code.equalsIgnoreCase(sessionCode));
		if (code.equalsIgnoreCase(sessionCode)) {
			session.removeAttribute("code");
			return renderSuccess("验证成功");
		}
		return renderError("验证码错误");
	}
}
