/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service;

import com.baomidou.mybatisplus.service.IService;
import mnu.bbs.domain.entity.User;

/**
 * @author xian
 * date 2018/4/12 14:22
 * desc
 */
public interface IUserService extends IService<User> {
	/**
	 * 插入用户头像
	 */
	boolean updateAvatar(String fileName,Integer id);
	
	/**
	 *
	 */
	boolean updateName(Integer id, String name);
	
	String selectEmail(Integer id,String value);
	
	boolean updatePassword(Integer id,String value);
}
