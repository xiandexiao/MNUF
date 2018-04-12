/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.util.Date;

/**
 * @author xian
 * date 2018/4/12 13:52
 * desc 角色类
 */

public class Role extends Super<Role> {
	private static final long serialVersionUID = 1L;
	/**
	 * 角色名
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	
	public Role() {}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public Integer getSort () {
		return sort;
	}
	
	public void setSort (Integer sort) {
		this.sort = sort;
	}
	
	public String getDescription () {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}
	
	public Integer getStatus () {
		return status;
	}
	
	public void setStatus (Integer status) {
		this.status = status;
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
		return "Role{" +
				"name='" + name + '\'' +
				", sort=" + sort +
				", description='" + description + '\'' +
				", status=" + status +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
