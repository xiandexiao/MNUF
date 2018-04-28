package mnu.bbs.domain.service;

import com.baomidou.mybatisplus.service.IService;
import mnu.bbs.domain.dto.CommentDto;
import mnu.bbs.domain.entity.Comment;

import java.util.List;

public interface ICommentService extends IService<Comment> {
	List<CommentDto> selectCommentsById(Integer id);
}
