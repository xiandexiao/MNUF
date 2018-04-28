/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.controller;

import mnu.bbs.common.controller.BaseController;
import mnu.bbs.common.result.JsonResult;
import mnu.bbs.domain.dto.CommentDto;
import mnu.bbs.domain.dto.TodayPostDto;
import mnu.bbs.domain.dto.UserPostDto;
import mnu.bbs.domain.entity.Post;
import mnu.bbs.domain.service.ICommentService;
import mnu.bbs.domain.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * @author xian
 * date 2018/4/17 20:19
 * desc
 */
@Controller
public class PostController extends BaseController{
	
	@Autowired
	private IPostService postService;
	
	@Autowired
	private ICommentService commentService;
	
	@Value("${file-upload-path}")
	private String fileUploadPath;
	
	@GetMapping("/push-post")
	public String push(Model model) {
		model.addAttribute("user",getUpdatedUser());
		return "/push-post";
	}
	
	@PostMapping("/pushPost")
	@ResponseBody
	public String push(HttpServletRequest request){
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		String title = request.getParameter("title");
		System.out.println(title);
		String content = request.getParameter("content");
		System.out.println(content);
		String UUIDFileName = "";
		MultipartFile file = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			String fileName = file.getOriginalFilename();
			UUIDFileName = UUID.randomUUID()  + fileName;
			if (!file.isEmpty()) {
				Path filePath = Paths.get(fileUploadPath,"post",UUIDFileName);
				try {
					file.transferTo(filePath.toFile());
				} catch (IOException e) {
					e.printStackTrace();
					return "写入文件" + fileName + "错误";
				}
			} else {
				return "上传文件 " + fileName + " 失败，内容为空";
			}
		}
		Post post = new Post();
		post.setUserId(getCurrentLoginId());
		post.setUrl(UUIDFileName);
		post.setTitle(title);
		post.setContent(content);
		post.setType("正常");
		postService.insert(post);
		return "发布成功";
	}
	
	/**
	* Author: xian
	* Date 2018/4/20 14:56
	* Description 根据贴子ID获取贴子详细内容
	*/
	@GetMapping("/detail-post/{id}")
	public String detailPost(@PathVariable("id") int id,Model model) {
		//对应用户及贴子的详细信息
		UserPostDto post = postService.selectPostById (id);
		model.addAttribute("post",post);
		model.addAttribute("user",getCurrentUser());
		//对应贴子下的用户评论信息
		List<CommentDto> comments = commentService.selectCommentsById(id);
		model.addAttribute("comments",comments);
		return "/detail-post";
	}
	
	/**
	* Author: xian
	* Date 2018/4/20 14:36
	* Description 查询今日新增的贴子：id，title，userName,createTime
	*/
	@PostMapping("/selectTodayPost")
	@ResponseBody
	public List<TodayPostDto> getTodayPost() {
		List<TodayPostDto> posts = postService.selectTodayPost();
		return posts;
	}
	
	/**
	* Author: xian
	* Date 2018/4/21 15:42
	* Description 查询所有贴子 包括用户名
	*/
	@GetMapping("/post-detail")
	public String selectAllPost(Model model) {
		List<UserPostDto> posts = postService.selectAllPost();
		model.addAttribute("posts",posts);
		return "/post-detail";
	}
	
}


