/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import mnu.bbs.domain.entity.Permission;
import mnu.bbs.domain.mapper.PermissionMapper;
import mnu.bbs.domain.service.IPermissionService;
import org.springframework.stereotype.Service;

/**
 * @author xian
 * date 2018/4/12 14:27
 * desc
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper,Permission> implements IPermissionService{

}
