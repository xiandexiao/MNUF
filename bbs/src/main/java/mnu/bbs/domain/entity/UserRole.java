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
 * date 2018/4/12 14:03
 * desc 用户角色关系表
 */
@TableName("userRole")
public class UserRole extends Super<UserRole> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	private Long uid;
	/**
	 * 角色ID
	 */
	private Long rid;
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
	
	public Long getUid () {
		return uid;
	}
	
	public void setUid (Long uid) {
		this.uid = uid;
	}
	
	public Long getRid () {
		return rid;
	}
	
	public void setRid (Long rid) {
		this.rid = rid;
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
		return "UserRole{" +
				"uid=" + uid +
				", rid=" + rid +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
