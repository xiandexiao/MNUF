/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.util.Date;

/**
 * @author xian
 * date 2018/4/12 14:08
 * desc
 */
public class Post extends Super<Post> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 贴子名
	 */
	private String title;
	/**
	 * 贴子发布人名
	 */
	private Integer userId;
	/**
	 * 贴子类型
	 */
	private String type;
	/**
	 * 图片地址
	 */
	private String url;
	/**
	 * 贴子内容
	 */
	private String content;
	/**
	 * 访问量
	 */
	private Integer visited;
	/**
	 * 评论数量
	 */
	private Integer commentCount;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	
	public Post () {
	}
	
	public String getTitle () {
		return title;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public Integer getUserId () {
		return userId;
	}
	
	public void setUserId (Integer userId) {
		this.userId = userId;
	}
	
	public String getType () {
		return type;
	}
	
	public void setType (String type) {
		this.type = type;
	}
	
	public String getContent () {
		return content;
	}
	
	public void setContent (String content) {
		this.content = content;
	}
	
	public String getUrl () {
		return url;
	}
	
	public void setUrl (String url) {
		this.url = url;
	}
	
	public Integer getCommentCount () {
		return commentCount;
	}
	
	public void setCommentCount (Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	public Integer getVisited () {
		return visited;
	}
	
	public void setVisited (Integer visited) {
		this.visited = visited;
	}
	
	public Date getCreateTime () {
		return createTime;
	}
	
	public void setCreateTime (Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getUpdateTime () {
		return updateTime;
	}
	
	public void setUpdateTime (Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString () {
		return "Post{" +
				"title='" + title + '\'' +
				", userId='" + userId + '\'' +
				", type=" + type +
				", url='" + url + '\'' +
				", content='" + content + '\'' +
				", visited='" + visited + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", commentCount=" + commentCount +
				'}';
	}
}
