package org.xm06.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.query.GroupQuery;
import org.xm06.pms.model.UserGroupModel;
import org.xm06.pms.service.GroupService;
import org.xm06.pms.vo.Group;

import java.util.List;


@Controller
@RequestMapping("/group")
@Api(value = "GroupController", tags = "小组模块接口")
@ApiSupport(author = "914577981@qq.com")
public class GroupController extends BaseController {

    @Autowired
    GroupService groupService;

    /**
     * 创建小组
     * @param group
     * @return
     */
    @RequestMapping(value = "/add",produces = "application/json;charset=utf-8",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation("根据上传的小组信息，创建小组接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName", value = "创建的小组名",required = true,dataType = "String",dataTypeClass = String.class),
            @ApiImplicitParam(name = "managerId", value = "创建人的id",required = true,dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "described", value = "小组描述",dataType = "String",dataTypeClass = String.class)
    })
    public ResultInfo createGroup(Group group){
        Group addGroup = groupService.createGroup(group);

        systemRecordService.addGroupCreateCount();
        return success("创建小组成功", 200, addGroup);
    }

    @RequestMapping(value = "/alterGroupInfo",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultInfo alterGroupInfo(Integer groupId, Integer userId,
                                     String groupName, String described){

        groupService.alterGroupInfo(groupId,userId,groupName,described);
        return success("修改成功", 200, null);
    }

    /**
     * 成员加入小组
     * @param userId 成员id
     * @param groupId   小组id
     * @param invitationCode    小组邀请码
     * @return
     */
    @RequestMapping(value = "/addUser",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("根据用户id，小组id，将用户添加进小组接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "加入用户的id", required = true, dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "groupId", value = "加入小组的id", required = true, dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "invitationCode", value = "小组的邀请码", required = true, dataType = "String",dataTypeClass = String.class),
    })
    public ResultInfo userAddIntoGroup(Integer userId, Integer groupId, String invitationCode){
        UserGroupModel userGroupModel = groupService.userAddIntoGroup(userId, groupId, invitationCode);
        return success("加入小组成功", 200, userGroupModel);
    }


    /**
     * 根据id 查找小组的全部信息，包括组员信息
     * @param group
     * @return
     */
    @RequestMapping(value = "/queryAllInfo",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("根据小组id，查找小组的全部信息，包括组员")
    @ApiImplicitParam(name = "id", value = "小组的id", required = true, dataType = "Integer",dataTypeClass = Integer.class)
    public ResultInfo queryAllInfo(Group group){
        Group groupAllInfoById = groupService.findGroupAllInfoById(group.getId());
        return success("查询成功", 200, groupAllInfoById);
    }

    @RequestMapping(value = "/queryByGroupName",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("根据小组名，查找小组的全部信息接口")
    @ApiImplicitParam(name = "groupName", value = "小组的名字", required = true,dataType = "String",dataTypeClass = String.class)
    public ResultInfo queryByGroupName(String groupName) {
        Group group = groupService.findGroupAllInfoByGroupName(groupName);
        return success("查询成功", 200, group);
    }


    /**
     * 分页查找全部小组信息
     * @param groupQuery
     * @return
     */
    @RequestMapping(value = "/pagequeryAll",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("分页查找全部小组的信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "分页查找的页数,默认1", dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name="limit",value = "分页查找每页的数量，默认10", dataType = "Integer",dataTypeClass = Integer.class)
    })
    public PageInfo<Group> groupPageQuery(GroupQuery groupQuery){ ;
        PageHelper.startPage(groupQuery.getPage(), groupQuery.getLimit());
        List<Group> all = groupService.findAll();
        return new PageInfo<>(all);
    }

    /**
     * 查询用户加入的全部小组
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryJoinedAll",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation(" 查询用户加入的全部小组接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value = "用户id", required = true, dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo queryJoinedAll(Integer userId){
        List<Group> all = groupService.findUserJoinedGroupAll(userId);
        return success("查询成功", 200, all);
    }

    /**
     * 查询用户管理的全部小组，包含成员信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryMyGroupAll",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("查询用户管理的全部小组，包含成员信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value = "用户id", required = true, dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo queryMyGroupAll(Integer userId){
        List<Group> all = groupService.queryMyGroupAll(userId);
        return success("查询成功", 200, all);
    }

    /**
     * 查询用户管理的全部小组，包含成员信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryMyGroup",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("查询用户管理的全部小组，不包含成员信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value = "用户id", required = true, dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo queryMyGroup(Integer userId){
        List<Group> all = groupService.queryMyGroup(userId);
        return success("查询成功", 200, all);
    }

    /**
     * 根据管理员id分页查找该管理员管理的小组信息
     * @param groupQuery
     * @return
     */
    @RequestMapping(value = "/pagequeryByManagerId",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("分页查询用户管理的全部小组接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="managerId",value = "用户id", required = true, dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name="page",value = "分页查找的页数,默认1", dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name="limit",value = "分页查找每页的数量，默认10", dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo pagequeryByManagerId(GroupQuery groupQuery) {

        PageInfo<Group> info = groupService.findGroupByManagerId(groupQuery);
        return success("查询成功", 200, info);
    }

    @RequestMapping(value = "/pagequeryUerJoiedGroup",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("分页查询用户加入的全部小组接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value = "用户id", required = true, dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name="page",value = "分页查找的页数,默认1", dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name="limit",value = "分页查找每页的数量，默认10", dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo pagequeryUerJoined(GroupQuery groupQuery) {
        PageInfo<Group> info = groupService.findUserJoinedGroup(groupQuery);
        return success("查询成功", 200, info);
    }


    @RequestMapping(value = "/findAll",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("查询全部小组接口")
    public ResultInfo findAll(){
        List<Group> all = groupService.findAll();
        return success("查询成功", 200, all);
    }

    /**
     * 根据小组id获取邀请码
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/invitationCode",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("根据小组id获取邀请码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="managerId",value = "用户id,为小组管理用户", required = true, dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name="groupId",value = "小组id", required = true, dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo getInvitationCode(Integer groupId, Integer managerId) {
        String invitationCode = groupService.getInvitationCode(groupId, managerId);
        return success("获取小组邀请码成功", 200, invitationCode);
    }

    @RequestMapping(value = "/removeMember",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("小组的管理员从小组中踢除用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="managerId",value = "用户id,为小组管理用户", required = true, dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name="groupId",value = "小组id", required = true,dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name="userId",value = "用户id，踢除用户的id", required = true, dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo removeMember(Integer managerId, Integer groupId, Integer userId){
        String removeMember = groupService.removeMember(managerId, groupId, userId);
        return success("移除成员成功", 200, removeMember);
    }

    @RequestMapping(value = "/dissolveGroup",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("小组的管理员解散小组接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="managerId",value = "用户id,为小组管理用户", required = true, dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name="groupId",value = "小组id", required = true, dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo dissolveGroup(Integer managerId, Integer groupId){
        String dissolveGroup = groupService.dissolveGroup(managerId, groupId);
        return success("解散小组成功", 200, dissolveGroup);
    }
}
