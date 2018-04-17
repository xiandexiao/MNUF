/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import mnu.bbs.domain.entity.Post;
import mnu.bbs.domain.mapper.PostMapper;
import mnu.bbs.domain.service.IPostService;
import org.springframework.stereotype.Service;

/**
 * @author xian
 * date 2018/4/17 22:45
 * desc
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper,Post> implements IPostService {
}
