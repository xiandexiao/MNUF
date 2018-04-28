/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author xian
 * date 2018/4/18 21:07
 * desc
 */
public class UserPostDto {
	
	private Integer userId; //发布贴子的用户ID
	private Integer postId;//被发布帖子的ID
	private String userAvatar;//发布帖子的用户头像
	private String userName;//发布帖子的用户名
	private String postTitle;//贴子名
	private Integer postVisited;//贴子访问量
	private String postUrl;//贴子图片
	private Integer commentCount;//贴子的评论数
	private String postContent;//贴子内容
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;//贴子发布时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date updateTime;//贴子更新时间
	private String postType;//贴子类型
	
	public UserPostDto () {
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
	
	public String getUserAvatar () {
		return userAvatar;
	}
	
	public void setUserAvatar (String userAvatar) {
		this.userAvatar = userAvatar;
	}
	
	public Integer getPostVisited () {
		return postVisited;
	}
	
	public void setPostVisited (Integer postVisited) {
		this.postVisited = postVisited;
	}
	
	public String getUserName () {
		return userName;
	}
	
	public void setUserName (String userName) {
		this.userName = userName;
	}
	
	public String getPostTitle () {
		return postTitle;
	}
	
	public void setPostTitle (String postTitle) {
		this.postTitle = postTitle;
	}
	
	public Date getCreateTime () {
		return createTime;
	}
	
	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}
	
	public String getPostUrl () {
		return postUrl;
	}
	
	public void setPostUrl (String postUrl) {
		this.postUrl = postUrl;
	}
	
	public String getPostContent () {
		return postContent;
	}
	
	public void setPostContent (String postContent) {
		this.postContent = postContent;
	}
	
	public Integer getCommentCount () {
		return commentCount;
	}
	
	public void setCommentCount (Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	public Date getUpdateTime () {
		return updateTime;
	}
	
	public void setUpdateTime (Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getPostType () {
		return postType;
	}
	
	public void setPostType (String postType) {
		this.postType = postType;
	}
	
	
	@Override
	public String toString () {
		return "UserPostDto{" +
				"userId=" + userId +
				", postId=" + postId +
				", userAvatar='" + userAvatar + '\'' +
				", userName='" + userName + '\'' +
				", postTitle='" + postTitle + '\'' +
				", postVisited=" + postVisited +
				", postUrl='" + postUrl + '\'' +
				", commentCount=" + commentCount +
				", postContent='" + postContent + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", postType='" + postType + '\'' +
				'}';
	}
}
