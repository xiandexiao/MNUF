/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.config.mybatis;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xian
 * date 2018/4/11 17:48
 * desc
 */
@Configuration
@MapperScan({"mnu.bbs.domain.mapper*"})
public class MybatisPlusConfig {
	
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
		return paginationInterceptor;
	}
}
