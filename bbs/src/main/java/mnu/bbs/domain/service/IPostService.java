package mnu.bbs.domain.service;

import com.baomidou.mybatisplus.service.IService;
import mnu.bbs.domain.entity.Post;

import java.util.List;

public interface IPostService extends IService<Post> {
	
	List<Post> selectHotPosts(int record);
}
