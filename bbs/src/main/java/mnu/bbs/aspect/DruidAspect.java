/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.aspect;

import mnu.bbs.config.datasource.DruidConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author xian
 * date 2018/4/11 16:46
 * desc
 */
@Aspect
@Component
public class DruidAspect {
	private static final Logger log = LoggerFactory.getLogger(DruidConfig.class);
	
	@Pointcut("execution(public * mnu.bbs.config.datasource.DruidConfig.*(..))")
	private void log() {}
	
	@Before("log()")
	private void doBefore(JoinPoint point) {
		log.info("Druid连接池配置进行中... ...");
	}
	
	@After("log()")
	private void doAfter(JoinPoint point) {
		log.info("args={}",point.getArgs());
	}
}
