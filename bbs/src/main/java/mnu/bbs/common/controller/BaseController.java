package mnu.bbs.common.controller;

import mnu.bbs.common.result.JsonResult;
import mnu.bbs.domain.entity.User;
import mnu.bbs.domain.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
* Author: xian
* Date 2018/4/11 16:33
* Description 基础控制器，进行数据包装
*/
public class BaseController {
    @Autowired
    private UserMapper userMapper;
    /**
     * 渲染失败数据
     *
     * @return JsonResult [false, 500, null, null]
     */
    protected JsonResult renderError() {
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        result.setStatus("500");
        return result;
    }

    /**
     * 渲染失败数据（带消息）
     *
     * @param msg 需要返回的消息
     * @return JsonResult [false, 500, msg, null]
     */
    protected JsonResult renderError(String msg) {
        JsonResult result = renderError();
        result.setMsg(msg);
        return result;
    }

    /**
     * 渲染成功数据
     *
     * @return JsonResult [true, 200, null, null]
     */
    protected JsonResult renderSuccess() {
        JsonResult result = new JsonResult();
        result.setSuccess(true);
        result.setStatus("200");
        return result;
    }

    /**
     * 渲染成功数据（带信息）
     *
     * @param msg 需要返回的信息
     * @return JsonResult [true, 200, msg, null]
     */
    protected JsonResult renderSuccess(String msg) {
        JsonResult result = renderSuccess();
        result.setMsg(msg);
        return result;
    }

    /**
     * 渲染成功数据（带数据）
     *
     * @param obj 需要返回的对象
     * @return JsonResult [true, 200, null, obj]
     */
    protected JsonResult renderSuccess(Object obj) {
        JsonResult result = renderSuccess();
        result.setObj(obj);
        return result;
    }

    /**
     * 登录用户名
     */
    public String getCurrentLoginUsername() {
        Subject currentUser = SecurityUtils.getSubject();
        User user = currentUser.getPrincipals().oneByType(User.class);
        return user.getName();
    }

    /**
     * 登陆用户id
     * @return
     */
    public Integer getCurrentLoginId(){
        Subject currentUser = SecurityUtils.getSubject();
        User user = currentUser.getPrincipals().oneByType(User.class);
        return user.getId();
    }

    /**
     * 登录用户对象
     */
    public User getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
       User user = currentUser.getPrincipals().oneByType(User.class);
        return user;
    }
    
    public User getUpdatedUser() {
        User user = userMapper.selectById(getCurrentLoginId());
        return user;
    }
    
}
