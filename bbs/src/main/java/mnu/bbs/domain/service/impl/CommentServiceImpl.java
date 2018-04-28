/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import mnu.bbs.domain.dto.CommentDto;
import mnu.bbs.domain.entity.Comment;
import mnu.bbs.domain.mapper.CommentMapper;
import mnu.bbs.domain.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xian
 * date 2018/4/19 15:27
 * desc
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper,Comment> implements ICommentService{
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public List<CommentDto> selectCommentsById (Integer id) {
		return commentMapper.selectCommentsById(id);
	}
}
