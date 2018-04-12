/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import mnu.bbs.domain.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xian
 * date 2018/4/12 14:19
 * desc 权限DAO层
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
	/**
	 * 查询用户拥有那些权限
	 * param uid
	 */
	List<Permission> findUserPermission(Integer uid);
}
