/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.config.mybatis;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Timestamp;

/**
 * @author xian
 * date 2018/4/11 17:43
 * desc
 */
public class MyMetaObjectHandler extends MetaObjectHandler {
	
	/**
	 *  user 表 字段为空自动填充
	 */
	@Override
	public void insertFill (MetaObject metaObject) {
		// 测试下划线
		Object testType = getFieldValByName("createTime", metaObject);//mybatis-plus版本2.0.9+
		if (testType == null) {
			setFieldValByName("createTime", new Timestamp(System.currentTimeMillis()), metaObject);//mybatis-plus版本2.0.9+
		}
		
	}
	
	/**
	 * Description user表更新 自动填充时间
	 */
	@Override
	public void updateFill (MetaObject metaObject) {
		//mybatis-plus版本2.0.9+
		setFieldValByName("updateTime", new Timestamp(System.currentTimeMillis()), metaObject);
	}
}
