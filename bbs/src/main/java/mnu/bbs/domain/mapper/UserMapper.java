/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import mnu.bbs.domain.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author xian
 * date 2018/4/12 14:18
 * desc 用户DAO层
 */
@Repository
public interface UserMapper extends BaseMapper<User>{
}
