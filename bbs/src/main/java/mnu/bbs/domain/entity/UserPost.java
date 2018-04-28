/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author xian
 * date 2018/4/18 12:43
 * desc 用户发布的贴子
 */

@TableName("UserPost")
public class UserPost extends Super<UserPost> {
	
	private Integer userId;
	
	private Integer postId;
	
	public UserPost() {}
	
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
	
	@Override
	public String toString () {
		return "UserComment{" +
				"userId=" + userId +
				", postId=" + postId +
				'}';
	}
	
}
