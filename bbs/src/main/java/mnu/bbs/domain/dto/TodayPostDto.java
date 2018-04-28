/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author xian
 * date 2018/4/20 14:27
 * desc
 */
public class TodayPostDto {
	private Integer postId;
	private Integer userId;
	private String title;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;
	
	public TodayPostDto () {
	}
	
	public Integer getPostId () {
		return postId;
	}
	
	public void setPostId (Integer postId) {
		this.postId = postId;
	}
	
	public Integer getUserId () {
		return userId;
	}
	
	public void setUserId (Integer userId) {
		this.userId = userId;
	}
	
	public String getTitle () {
		return title;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public Date getCreateTime () {
		return createTime;
	}
	
	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}
}
