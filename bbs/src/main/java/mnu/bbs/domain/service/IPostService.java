package mnu.bbs.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import mnu.bbs.domain.dto.TodayPostDto;
import mnu.bbs.domain.dto.UserPostDto;
import mnu.bbs.domain.entity.Post;

import java.util.List;

public interface IPostService extends IService<Post> {
	
	List<UserPostDto> selectHotPosts(int record);
	
	UserPostDto selectPostById(Integer id);
	
	List<TodayPostDto> selectTodayPost();
	
	List<Post> selectPostByUserId(Integer id);
	
	List<UserPostDto> selectAllPost();
	
	List<UserPostDto> selectNewPost(int record);
	
	Page<UserPostDto> selectUserPostPage(Page<UserPostDto> page,String type,String sort);
}
