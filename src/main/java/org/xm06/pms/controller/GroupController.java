package org.xm06.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class GroupController extends BaseController {

    @Autowired
    GroupService groupService;

    /**
     * 创建小组
     * @param group
     * @return
     */
    @RequestMapping(value = "/add",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo createGroup(Group group){
        Group addGroup = groupService.createGroup(group);
        return success("创建小组成功", 200, addGroup);
    }

    /**
     * 成员加入小组
     * @param userId 成员id
     * @param groupId   小组id
     * @param invitationCode    小组邀请码
     * @return
     */
    @RequestMapping(value = "/addUser",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo userAddIntoGroup(Integer userId, Integer groupId, String invitationCode){
        UserGroupModel userGroupModel = groupService.userAddIntoGroup(userId, groupId, invitationCode);
        return success("加入小组成功", 200, userGroupModel);
    }


    /**
     * 根据id 查找小组的全部信息，包括组员信息
     * @param group
     * @return
     */
    @RequestMapping(value = "/queryAllInfo",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo queryAllInfo(Group group){
        Group groupAllInfoById = groupService.findGroupAllInfoById(group.getId());
        return success("查询成功", 200, groupAllInfoById);
    }

    @RequestMapping(value = "/queryByGroupName",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo queryByGroupName(String groupName) {
        Group group = groupService.findGroupAllInfoByGroupName(groupName);
        return success("查询成功", 200, group);
    }

    /**
     * 分页查找全部小组信息
     * @param groupQuery
     * @return
     */
    @RequestMapping(value = "/pagequeryAll",produces = "application/json;charset=utf-8")
    @ResponseBody
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
    @RequestMapping(value = "/queryJoinedAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo queryJoinedAll(Integer userId){
        List<Group> all = groupService.findUserJoinedGroupAll(userId);
        return success("查询成功", 200, all);
    }
    /**
     * 查询用户管理的全部小组
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryMyGroupAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo queryMyGroupAll(Integer userId){
        List<Group> all = groupService.queryMyGroupAll(userId);
        return success("查询成功", 200, all);
    }

    /**
     * 根据管理员id分页查找该管理员管理的小组信息
     * @param groupQuery
     * @return
     */
    @RequestMapping(value = "/pagequeryByManagerId",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo pagequeryByManagerId(GroupQuery groupQuery) {

        PageInfo<Group> info = groupService.findGroupByManagerId(groupQuery);
        return success("查询成功", 200, info);
    }

    @RequestMapping(value = "/pagequeryUerJoiedGroup",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo pagequeryUerJoined(GroupQuery groupQuery) {
        PageInfo<Group> info = groupService.findUserJoinedGroup(groupQuery);
        return success("查询成功", 200, info);
    }


    @RequestMapping(value = "/findAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo findAll(){
        List<Group> all = groupService.findAll();
        return success("查询成功", 200, all);
    }

    /**
     * 根据小组id获取邀请码
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/invitationCode",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo getInvitationCode(Integer groupId, Integer managerId) {
        String invitationCode = groupService.getInvitationCode(groupId, managerId);
        return success("获取小组邀请码成功", 200, invitationCode);
    }

    @RequestMapping(value = "/removeMember",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo removeMember(Integer managerId, Integer groupId, Integer userId){
        String removeMember = groupService.removeMember(managerId, groupId, userId);
        return success("移除成员成功", 200, removeMember);
    }

    @RequestMapping(value = "/dissolveGroup",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo dissolveGroup(Integer managerId, Integer groupId){
        String dissolveGroup = groupService.dissolveGroup(managerId, groupId);
        return success("解散小组成功", 200, dissolveGroup);
    }
}
