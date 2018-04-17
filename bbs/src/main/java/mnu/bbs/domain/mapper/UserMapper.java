/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import mnu.bbs.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author xian
 * date 2018/4/12 14:18
 * desc 用户DAO层
 */
@Repository
public interface UserMapper extends BaseMapper<User>{
	
	@Update("UPDATE user SET avatar=#{fileName} WHERE id=#{id}")
	int updateAvatar(@Param("fileName") String fileName,@Param("id") Integer id);
	
	@Update("UPDATE user set name=#{name} WHERE id=#{id}")
	int updateName(@Param("id") Integer id, @Param("name") String name);
	
	@Select("SELECT email FROM user WHERE id=#{id}")
	String selectEmail(@Param("id") Integer id);
	
	@Update("UPDATE user set password=#{password},salt=#{salt} WHERE id=#{id}")
	int updatePassword(@Param("id") Integer id, @Param("password") String password,@Param("salt") String salt);
}
