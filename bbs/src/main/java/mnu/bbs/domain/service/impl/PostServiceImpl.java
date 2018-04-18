/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import mnu.bbs.domain.entity.Post;
import mnu.bbs.domain.mapper.PostMapper;
import mnu.bbs.domain.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xian
 * date 2018/4/17 22:45
 * desc
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper,Post> implements IPostService {
	
	@Autowired
	private PostMapper postMapper;
	/**
	* Author: xian
	* Date 2018/4/18 19:54
	* 通过访问量查询指定条数的条数
	*/
	@Override
	public List<Post> selectHotPosts (int record) {
		return postMapper.selectHotPosts(record);
	}
}
