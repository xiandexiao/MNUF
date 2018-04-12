/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * @author xian
 * date 2018/4/11 16:16
 * 实体父类
 */
public class Super<T extends Model> extends Model<T> {
	private static final long serialVersionUID = 1L;
	/**
	 * 所有实体类主键
	 * */
	private Integer id;
	
	public Integer getId () {
		return id;
	}
	
	public void setId (Integer id) {
		this.id = id;
	}
	
	/**
	 * Getter and Setter
	 * */
	
	@Override
	protected Serializable pkVal () {
		return this.id;
	}
}
