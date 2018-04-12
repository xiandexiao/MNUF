/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.util.Date;

/**
 * @author xian
 * date 2018/4/12 14:05
 * desc
 */
@TableName("RolePermission")
public class RolePermission extends Super<RolePermission>{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色ID
	 */
	private Long rid;
	/**
	 * 权限ID
	 */
	private Long pid;
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
	
	public Long getRid () {
		return rid;
	}
	
	public void setRid (Long rid) {
		this.rid = rid;
	}
	
	public Long getPid () {
		return pid;
	}
	
	public void setPid (Long pid) {
		this.pid = pid;
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
		return "RolePermission{" +
				"rid=" + rid +
				", pid=" + pid +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
