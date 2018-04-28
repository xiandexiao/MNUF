package mnu.bbs.domain.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import mnu.bbs.domain.dto.CommentDto;
import mnu.bbs.domain.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment>{
	
	@Select("SELECT u.id AS 'userId',u.name AS 'userName',u.avatar AS 'userAvatar',c.createTime AS 'createTime' ," +
			"c.comment AS 'comment'FROM user u,comment c WHERE c.userId = u.id AND c.postId = #{id} ORDER BY c.createTime ASC ;")
	List<CommentDto> selectCommentsById(@Param("id") Integer id);
}
