/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.controller;

import mnu.bbs.common.controller.BaseController;
import mnu.bbs.common.result.JsonResult;
import mnu.bbs.domain.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author xian
 * date 2018/4/15 15:21
 * desc
 */
@Controller
public class FileUploadController extends BaseController{
	private static final transient Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private IUserService userService;
	
	@Value("${file-upload-path}")
	private String fileUploadPath;
	
	/**
	 * 文件上传
	 */
	@PostMapping(value = "/uploadAvatar")
	@ResponseBody
	public JsonResult uploadAvatar(@RequestParam("avatar") MultipartFile file) {
		String id = getCurrentLoginId() + "";
		String fileName = id + ".jpg";
		try {
			if (file.isEmpty()) {
				return renderError("文件不能为空");
			}
		Path dest = Paths.get(fileUploadPath,"avatar",fileName);
		
		// 解决中文问题，liunx下中文路径，图片显示问题
		// fileName = UUID.randomUUID() + suffixName;
		logger.info("dest={}",dest);
			//操作数据库头像
			boolean result = userService.updateAvatar(fileName,getCurrentLoginId());
			if(result) {
				try {
					Files.delete(dest);
				}catch (NoSuchFileException e) {
					file.transferTo(dest.toFile());
					return renderSuccess("上传成功");
				}
				file.transferTo(dest.toFile());
				return renderSuccess("上传成功");
			}else {
				return renderError("上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderError("上传失败");
	}
}
