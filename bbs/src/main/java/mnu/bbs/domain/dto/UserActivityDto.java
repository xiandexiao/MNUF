/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.dto;

import java.util.Date;

/**
 * @author xian
 * date 2018/4/20 23:52
 * desc
 */
public class UserActivityDto {
	private Integer userId;//用户ID
	private Integer postId;//贴子ID
	private String name;//用户名
	private String avatar;//用户头像
	private String status;//用户状态
	private String email;//用户邮箱
	private String number;//用户学号
	private String title;//贴子标题
	private String content;//贴子内容
	private Date createTime;//发布时间
	
	public UserActivityDto () {
	}
	
	public Integer getUserId () {
		return userId;
	}
	
	public void setUserId (Integer userId) {
		this.userId = userId;
	}
	
	public Integer getPostId () {
		return postId;
	}
	
	public void setPostId (Integer postId) {
		this.postId = postId;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getAvatar () {
		return avatar;
	}
	
	public void setAvatar (String avatar) {
		this.avatar = avatar;
	}
	
	public String getStatus () {
		return status;
	}
	
	public void setStatus (String status) {
		this.status = status;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getNumber () {
		return number;
	}
	
	public void setNumber (String number) {
		this.number = number;
	}
	
	public String getTitle () {
		return title;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public String getContent () {
		return content;
	}
	
	public void setContent (String content) {
		this.content = content;
	}
	
	public Date getCreateTime () {
		return createTime;
	}
	
	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString () {
		return "UserActivityDao{" +
				"userId=" + userId +
				", postId=" + postId +
				", name='" + name + '\'' +
				", avatar='" + avatar + '\'' +
				", status='" + status + '\'' +
				", email='" + email + '\'' +
				", number='" + number + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
