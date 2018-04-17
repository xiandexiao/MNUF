/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.dto;

import mnu.bbs.domain.entity.User;

/**
 * @author xian
 * date 2018/4/16 13:13
 * desc 用户DTO
 */
public class UserDto {
	
	private Integer id;
	
	private String name;
	
	public UserDto () {
	}
	
	public UserDto (Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Integer getId () {
		return id;
	}
	
	public void setId (Integer id) {
		this.id = id;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	@Override
	public String toString () {
		return "UserDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
