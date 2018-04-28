package mnu.bbs.domain.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import mnu.bbs.domain.dto.UserPostDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostMapper extends BaseMapper<UserPostDto> {
	
	List<UserPostDto> selectUserPostList(Page<UserPostDto> page,@Param("type") String type,@Param("sort") String sort);
	
}
