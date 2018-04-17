/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.controller;

import mnu.bbs.common.controller.BaseController;
import mnu.bbs.common.result.JsonResult;
import mnu.bbs.domain.entity.Post;
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
	
	@Value("${file-upload-path}")
	private String fileUploadPath;
	
	@GetMapping("/push")
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
		post.setUserName(getCurrentLoginUsername());
		post.setUrl(UUIDFileName);
		post.setTitle(title);
		post.setContent(content);
		post.setType(0);
		postService.insert(post);
		return "发布成功";
	}
}
