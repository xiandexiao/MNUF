/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.dto;

import java.util.Date;

/**
 * @author xian
 * date 2018/4/19 15:21
 * desc
 */
public class CommentDto {
	private Integer userId;
	private String userName;
	private String userAvatar;
	private Date createTime;
	private String comment;
	
	public CommentDto () {
	}
	
	public Integer getUserId () {
		return userId;
	}
	
	public void setUserId (Integer userId) {
		this.userId = userId;
	}
	
	public String getUserName () {
		return userName;
	}
	
	public void setUserName (String userName) {
		this.userName = userName;
	}
	
	public String getUserAvatar () {
		return userAvatar;
	}
	
	public void setUserAvatar (String userAvatar) {
		this.userAvatar = userAvatar;
	}
	
	public Date getCreateTime () {
		return createTime;
	}
	
	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}
	
	public String getComment () {
		return comment;
	}
	
	public void setComment (String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString () {
		return "CommentDto{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", userAvatar='" + userAvatar + '\'' +
				", createTime=" + createTime +
				", comment='" + comment + '\'' +
				'}';
	}
}
