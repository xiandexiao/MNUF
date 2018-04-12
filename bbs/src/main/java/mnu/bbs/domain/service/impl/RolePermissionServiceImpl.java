/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import mnu.bbs.domain.entity.RolePermission;
import mnu.bbs.domain.mapper.RolePermissionMapper;
import mnu.bbs.domain.service.IRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * @author xian
 * date 2018/4/12 14:29
 * desc
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper,RolePermission>
		implements IRolePermissionService {
}
