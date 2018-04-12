/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.entity;

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
	private String name;
	/**
	 * 贴子发布人名
	 */
	private String userName;
	/**
	 * 贴子类型
	 */
	private Integer type;
	/**
	 * 贴子内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getUserName () {
		return userName;
	}
	
	public void setUserName (String userName) {
		this.userName = userName;
	}
	
	public Integer getType () {
		return type;
	}
	
	public void setType (Integer type) {
		this.type = type;
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
	
	public Date getUpdateTime () {
		return updateTime;
	}
	
	public void setUpdateTime (Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString () {
		return "Post{" +
				"name='" + name + '\'' +
				", userName='" + userName + '\'' +
				", type=" + type +
				", content='" + content + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
