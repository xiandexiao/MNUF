/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import mnu.bbs.domain.entity.Role;
import mnu.bbs.domain.mapper.RoleMapper;
import mnu.bbs.domain.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @author xian
 * date 2018/4/12 14:24
 * desc
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements IRoleService {
}
