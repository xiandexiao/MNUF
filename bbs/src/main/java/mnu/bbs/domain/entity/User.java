/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author xian
 * date 2018/4/11 16:35
 * desc 用户实体类
 */
public class User extends Super<User> {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 加密盐
	 */
	private String salt;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 学号
	 */
	private String number;
	/**
	 * 用户状态：0 禁用 1 正常
	 */
	private String status;
	/**
	 * 头像:地址
	 */
	private String avatar;
	/**
	 * 创建时间
	 */
	@TableField( fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	/**
	 *  Constructor
	 */
	public User () {
	}
	
	/**
	 * Description Getter and Setter
	 */
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getSalt () {
		return salt;
	}
	
	public void setSalt (String salt) {
		this.salt = salt;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getStatus () {
		return status;
	}
	
	public void setStatus (String status) {
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
	
	public String getPassword () {
		return password;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public String getNumber () {
		return number;
	}
	
	public void setNumber (String number) {
		this.number = number;
	}
	
	
	public String getAvatar () {
		return avatar;
	}
	
	public void setAvatar (String avatar) {
		this.avatar = avatar;
	}
	
	@Override
	public String toString () {
		return "User{" +
				"name='" + name + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", email='" + email + '\'' +
				", number='" + number + '\'' +
				", status=" + status +
				", avatar='" + avatar + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
