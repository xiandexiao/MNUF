/*
 * Copyright (c) 2018, 绵阳师范学院8栋131
 */
package mnu.bbs.domain.controller;

import mnu.bbs.common.controller.BaseController;
import mnu.bbs.common.result.JsonResult;
import mnu.bbs.domain.entity.Comment;
import mnu.bbs.domain.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xian
 * date 2018/4/19 17:14
 * desc
 */

@Controller
public class CommentController extends BaseController {
	
	@Autowired
	private ICommentService commentService;
	
	@PostMapping("/addComment")
	@ResponseBody
	public JsonResult addComment(@RequestBody Comment comment) {
		return commentService.insert(comment) ?  renderSuccess("评论成功") :  renderError("评论失败");
	}
}
