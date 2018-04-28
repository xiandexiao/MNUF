/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import mnu.bbs.domain.dto.TodayPostDto;
import mnu.bbs.domain.dto.UserPostDto;
import mnu.bbs.domain.entity.Post;
import mnu.bbs.domain.mapper.PostMapper;
import mnu.bbs.domain.mapper.UserPostMapper;
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
	
	@Autowired
	private UserPostMapper userPostMapper;
	/**
	* Author: xian
	* Date 2018/4/18 19:54
	* 通过访问量查询指定条数的条数
	*/
	@Override
	public List<UserPostDto> selectHotPosts (int record) {
		return postMapper.selectHotPosts(record);
	}
	
	@Override
	public UserPostDto selectPostById (Integer id) {
		return postMapper.selectPostById(id);
	}
	
	@Override
	public List<TodayPostDto> selectTodayPost () {
		return postMapper.selectTodayPost();
	}
	
	@Override
	public List<Post> selectPostByUserId (Integer id) {
		return postMapper.selectPostByUserId(id);
	}
	
	@Override
	public List<UserPostDto> selectAllPost () {
		return postMapper.selectAllPost();
	}
	
	@Override
	public List<UserPostDto> selectNewPost (int record) {
		return postMapper.selectNewPost(record);
	}
	
	@Override
	public Page<UserPostDto> selectUserPostPage (Page<UserPostDto> page, String type,String sort) {
		page.setRecords(userPostMapper.selectUserPostList(page,type,sort));
		return page;
	}
}
