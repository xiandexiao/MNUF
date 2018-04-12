/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * @author xian
 * date 2018/4/11 16:35
 * desc 用户实体类
 */
public class User extends Super<User> {
	
	/**
	 * Description 用户名
	 */
	@TableField("name")
	private String name;
	
	/**
	 * Description Constructor
	 */
	public User () {
	}
	
	/**
	 * Description Getter and Setter
	 */
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
}
