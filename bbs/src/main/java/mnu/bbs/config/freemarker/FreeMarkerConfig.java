/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.config.freemarker;

import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author xian
 * date 2018/4/12 15:06
 * desc FreeMarker Shiro 标签
 */
public class FreeMarkerConfig {
	@Autowired
	private freemarker.template.Configuration configuration;
	
	@PostConstruct
	public void setSharedVariable() {
		try {
			configuration.setSharedVariable("shiro", new ShiroTags());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
