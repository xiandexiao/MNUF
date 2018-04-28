/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.util.Date;

/**
 * @author xian
 * date 2018/4/18 13:18
 * desc 用户在贴子下的评论
 */
public class Comment extends Super<Comment> {
	
	private Integer userId;
	
	private Integer postId;
	
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	
	private String comment;
	
	public Comment () {}
	
	
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
		return "Comment{" +
				"userId=" + userId +
				", postId=" + postId +
				", createTime=" + createTime +
				", comment='" + comment + '\'' +
				'}';
	}
}
