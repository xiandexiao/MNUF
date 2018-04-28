/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.config.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xian
 * date 2018/4/12 15:04
 * desc 全局异常处理 Shiro注解跳转
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = org.apache.shiro.authz.UnauthorizedException.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		response.sendRedirect("/500.html");
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", request.getRequestURL());
//		mav.setViewName("admin/403");
		return mav;
	}
}
