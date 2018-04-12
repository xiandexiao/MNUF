/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import mnu.bbs.domain.entity.UserRole;
import mnu.bbs.domain.mapper.UserRoleMapper;
import mnu.bbs.domain.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author xian
 * date 2018/4/12 14:28
 * desc
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper,UserRole> implements IUserRoleService {
}
