/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.util.Date;

/**
 * @author xian
 * date 2018/4/12 13:56
 * desc
 */
public class Permission extends Super<Permission> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 权限名
	 */
	private String name;
	/**
	 * 类型 0、目录 1、菜单 2、按钮
	 */
	private Integer type;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 地址
	 */
	private String url;
	/**
	 * 权限编码
	 */
	private String permCode;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 状态 0、禁用 1、正常
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
	private Date updateDate;
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public Integer getType () {
		return type;
	}
	
	public void setType (Integer type) {
		this.type = type;
	}
	
	public Integer getSort () {
		return sort;
	}
	
	public void setSort (Integer sort) {
		this.sort = sort;
	}
	
	public String getUrl () {
		return url;
	}
	
	public void setUrl (String url) {
		this.url = url;
	}
	
	public String getPermCode () {
		return permCode;
	}
	
	public void setPermCode (String permCode) {
		this.permCode = permCode;
	}
	
	public String getIcon () {
		return icon;
	}
	
	public void setIcon (String icon) {
		this.icon = icon;
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
	
	public Date getUpdateDate () {
		return updateDate;
	}
	
	public void setUpdateDate (Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString () {
		return "Permission{" +
				"name='" + name + '\'' +
				", type=" + type +
				", sort=" + sort +
				", url='" + url + '\'' +
				", permCode='" + permCode + '\'' +
				", icon='" + icon + '\'' +
				", description='" + description + '\'' +
				", status=" + status +
				", createTime=" + createTime +
				", updateDate=" + updateDate +
				'}';
	}
}
