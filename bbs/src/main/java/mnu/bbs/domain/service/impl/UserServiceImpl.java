/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import mnu.bbs.domain.entity.User;
import mnu.bbs.domain.mapper.UserMapper;
import mnu.bbs.domain.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author xian
 * date 2018/4/12 14:23
 * desc
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {
}
