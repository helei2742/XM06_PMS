package org.xm06.pms.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.model.UserModel;
import org.xm06.pms.service.MailService;
import org.xm06.pms.service.UserService;
import org.xm06.pms.utils.UserIDBase64;
import org.xm06.pms.vo.User;


@Controller
@RequestMapping("/user")
@Api(value = "UserController",tags = "用户模块接口")
@ApiSupport(author = "914577981@qq.com")
public class UserController extends BaseController {

    @Autowired
    UserService userService;



    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8", method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "用户登录，需包含用户名密码",notes = "userName与userPwd必须")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "userPwd", value = "用户密码", required = true, dataTypeClass = String.class),
    })
    public ResultInfo login(User user){
        UserModel userModel = userService.login(user);
        return success("登录成功", 200, userModel);
    }



    @RequestMapping(value = "/findByIdStr",produces = "application/json;charset=utf-8", method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "根据用户的base64 编码id查找用户接口")
    @ApiImplicitParam(name="userIdStr",value = "id的base64编码",required = true,dataTypeClass = String.class)
    public ResultInfo findByIdStr(String userIdStr) {
        Integer userID = UserIDBase64.decoderUserID(userIdStr);
        User u = userService.findByIdStr(userID);

        systemRecordService.addLoginUserId(userID);
        return success("查询成功", 200, u);
    }

    @RequestMapping(value = "/add",produces = "application/json;charset=utf-8", method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("添加用户接口，会向用户邮箱发送确认邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "userPwd", value = "用户密码", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "email", value = "用户邮箱", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "trueName", value = "真实姓名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "phone", value = "手机号", required = false, dataTypeClass = String.class),
    })
    public ResultInfo addUser(User user) {
        UserModel userModel = userService.addUser(user);

        systemRecordService.addRegisterCount();
        return success("注册成功,请前往邮箱确认", 200, userModel);
    }

    @PostMapping("/alterUserInfo")
    @ResponseBody
    @ApiOperation(value = "修改用户信息接口",notes = "需传入（ userName，trueName,phone ）(需修改传入值，否则不传或null) 与user的id（必传）,")
    @ApiOperationSupport(includeParameters = {"user.userName","user.trueName","user.phone","user.id"})
    public ResultInfo alterUserInfo(@RequestBody User user){
        UserModel userModel = userService.alterUserInfo(user);
        return success("", 200, userModel);
    }

    @PostMapping("/dropUser")
    @ResponseBody
    @ApiOperation(value = "删除用户接口(目前只顺带删除了用户创建的小组)",notes = "需传入user的id和账户密码userPwd（必传）")
    @ApiOperationSupport(includeParameters = {"user.id","user.userPwd"})
    public ResultInfo dropUser(@RequestBody User user){
        userService.dropUser(user.getId(), user.getUserPwd());
        return success("", 200, null);
    }

    @RequestMapping(value = "/queryUserById",produces = "application/json;charset=utf-8", method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("根据id查找用户接口")
    @ApiImplicitParam(name="userId",required = true,value = "用户id",dataTypeClass = Integer.class)
    public ResultInfo queryUserById(Integer userId){
        User user = userService.selectByPrimaryKey(userId);
        user.setUserPwd("******");
        return success("查找成功", 200, user);
    }

    @RequestMapping(value = "/confirm/{id}", method={RequestMethod.GET,RequestMethod.POST})
    @ApiOperation("用户注册确认接口，根据路径上的id，进行注册确认")
    @ApiImplicitParam(name="id",value = "注册用户的id",required = true,paramType="path",dataTypeClass = Integer.class)
    public String confirm(@PathVariable(value = "id") Integer userId){
        System.out.println(userId);
        userService.confirm(userId);
        return "confirm";
    }



    @RequestMapping(value = "/alterPassword", produces = "application/json;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation(value = "修改密码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "修改用户的id",required = true,dataTypeClass = Integer.class),
            @ApiImplicitParam(name="oldPwd",value = "原密码",required = true,dataTypeClass = Integer.class),
            @ApiImplicitParam(name="newPwd",value = "新密码",required = true,dataTypeClass = Integer.class),
            @ApiImplicitParam(name="confirmPwd",value = "再次确认的新密码",required = true,dataTypeClass = Integer.class)
    })
    public ResultInfo alterPassword(int id, String oldPwd, String newPwd, String confirmPwd) {
        userService.alterPassword(id, oldPwd, newPwd, confirmPwd);
        return success("修改密码成功", 200, null);
    }

}

