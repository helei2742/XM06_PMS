package org.xm06.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.model.UserModel;
import org.xm06.pms.service.MailService;
import org.xm06.pms.service.UserService;
import org.xm06.pms.utils.UserIDBase64;
import org.xm06.pms.vo.User;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;



    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo login(User user){
        UserModel userModel = userService.login(user);
        return success("登录成功", 200, userModel);
    }

    @RequestMapping(value = "/findByIdStr",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo findByIdStr(String userIdStr) {
        Integer userID = UserIDBase64.decoderUserID(userIdStr);
        User u = userService.findByIdStr(userID);
        return success("查询成功", 200, u);
    }

    @RequestMapping(value = "/add",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo addUser(User user) {
        UserModel userModel = userService.addUser(user);
        return success("注册成功,请前往邮箱确认", 200, userModel);
    }

    @RequestMapping(value = "/queryUserById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo queryUserById(Integer userId){
        User user = userService.selectByPrimaryKey(userId);
        return success("查找成功", 200, user);
    }

    @RequestMapping("/confirm/{id}")
    public String confirm(@PathVariable(value = "id") Integer userId){
        System.out.println(userId);
        userService.confirm(userId);
        return "confirm";
    }


    @RequestMapping(value = "/alter",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo alterUser(User user) {
        UserModel alter = userService.alter(user);
        return success("修改信息成功", 200, alter);
    }

    @RequestMapping(value = "/alterPassword", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo alterPassword(int id, String oldPwd, String newPwd, String confirmPwd) {
        userService.alterPassword(id, oldPwd, newPwd, confirmPwd);
        return success("修改密码成功", 200, null);
    }

}

