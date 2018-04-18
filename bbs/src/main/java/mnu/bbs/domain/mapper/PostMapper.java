package mnu.bbs.domain.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import mnu.bbs.domain.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper extends BaseMapper<Post> {
	
	@Select("SELECT u.*,p.* FROM post p,user u ORDER BY visited DESC LIMIT #{record};")
	List<Post> selectHotPosts(@Param("record") int record);
}
