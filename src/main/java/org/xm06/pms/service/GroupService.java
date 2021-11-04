package org.xm06.pms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xm06.pms.dao.GroupMapper;
import org.xm06.pms.dao.UserMapper;
import org.xm06.pms.query.GroupQuery;
import org.xm06.pms.model.UserGroupModel;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.utils.Md5Util;
import org.xm06.pms.vo.Group;
import org.xm06.pms.vo.User;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GroupService {
    @Resource
    GroupMapper groupMapper;

    @Resource
    UserMapper userMapper;

    /**
     * 添加小组
     * @param group
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Group createGroup(Group group) {
        AssertUtil.isTrue(StringUtils.isBlank(group.getGroupName()), "组名不能为空");
        AssertUtil.isTrue(group.getManagerId() == null, "管理员id不能为空");

        Group selectByGroupName = groupMapper.findGroupByGroupName(group.getGroupName());
        AssertUtil.isTrue(selectByGroupName != null, "改组名已被使用");

        User selectManager = userMapper.selectByPrimaryKey(group.getManagerId());
        AssertUtil.isTrue(selectManager == null, "管理员不存在");

        group.setCreateDate(new Date());
        group.setUpdateDate(new Date());
        group.setIsValid(true);
        AssertUtil.isTrue(groupMapper.insertSelective(group)<1, "创建小组失败");

        Integer groupId = groupMapper.queryIdByName(group.getGroupName());

        groupMapper.userAddInGroup(group.getManagerId(), groupId);

        return group;
    }

    /**
     * 从组中移除用户
     * @param groupId   小组id
     * @param userId    用户id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public String removeMember(Integer managerId, Integer groupId, Integer userId){
        Group group = groupMapper.findGroupAllInfoById(groupId);
        AssertUtil.isTrue(group==null, "该小组不存在");

        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(user==null, "该用户不存在");
        AssertUtil.isTrue(managerId!=group.getManagerId(), "只有小组管理员才能移除成员");
        AssertUtil.isTrue(userId == group.getManagerId(), "不能移除小组管理员");

        boolean isContain = false;
        for (Object o : group.getMemberList()) {
            String s = JSON.toJSONString(o);
            User u  = JSONObject.parseObject(s, User.class);
            if(u.getId() == userId){
                isContain = true;
                break;
            }
        }

        AssertUtil.isTrue(!isContain, "小组中没有该成员");
        groupMapper.removeMember(userId, groupId);
        return user.getUserName();
    }


    /**
     * 根据小组id， 查找小组邀请码
     * @param groupId
     * @return
     */
    public String getInvitationCode(Integer groupId, Integer managerId){
        Group group = groupMapper.selectByPrimaryKey(groupId);
        AssertUtil.isTrue(group == null, "该小组不存在");
        AssertUtil.isTrue(group.getManagerId()!=managerId, "小组id与管理员id不服");
        return Md5Util.getInvitationCode(group.getGroupName(), group.getManagerId());
    }

    /**
     * 根据组 id查找该组的所有信息，包含成员的信息
     * @param id
     * @return
     */
    public Group findGroupAllInfoById(Integer id){
        AssertUtil.isTrue(id == null, "请输入小组id");
        Group groupAllInfo = groupMapper.findGroupAllInfoById(id);
        AssertUtil.isTrue(groupAllInfo == null, "该小组不存在");
        return groupAllInfo;
    }

    /**
     * 根据小组id查找小组所有组员的id
     * @param groupId
     * @return
     */
    public List<Integer> queryGroupMemberIdList(Integer groupId){
        AssertUtil.isTrue(groupId == null, "请输入小组id");
        Group groupAllInfo = groupMapper.findGroupAllInfoById(groupId);
        AssertUtil.isTrue(groupAllInfo == null, "该小组不存在");
        return groupMapper.queryMemberIdList(groupId);
    }

    /**
     * 根据小组名查找小组信息
     * @param groupName
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Group findGroupAllInfoByGroupName(String groupName) {
        AssertUtil.isTrue(StringUtils.isBlank(groupName), "请输入正确小组名");
        Group group = groupMapper.findGroupByGroupName(groupName);
        AssertUtil.isTrue(group == null, "该小组不存在");
        return group;
    }

    /**
     * 获取小组总数
     * @return
     */
    public Integer totalCount(){
        return groupMapper.totalCount();
    }


    /**
     * 查找所有的组，包含组里的成员信息
     * @return
     */
    public List<Group> findAll() {
        return groupMapper.findAll();
    }


    /**
     * 用户加入小组
     * @param userId
     * @param groupId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public UserGroupModel userAddIntoGroup(int userId, int groupId, String invitationCode){
        Group group = groupMapper.findGroupAllInfoById(groupId);
        AssertUtil.isTrue(group==null, "该小组不存在");

        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(user==null, "该用户不存在");

        AssertUtil.isTrue(StringUtils.isBlank(invitationCode), "请输入邀请码");

        String code = Md5Util.getInvitationCode(group.getGroupName(), group.getManagerId());
        AssertUtil.isTrue(!invitationCode.equals(code), "邀请码错误");

        boolean isContain = false;
        System.out.println(group.getMemberList());
        for (User member : group.getMemberList()) {
            if (member.getId() == userId) {
                isContain = true;
                break;
            }
        }

        AssertUtil.isTrue(isContain, "该用户已在小组中");


        groupMapper.userAddInGroup(userId, groupId);
        return new UserGroupModel(group.getGroupName(), groupId, user.getUserName(), userId);
    }


    /**
     * 分页查找管理员管理的组，包括成员信息
     * @param
     * @return
     */
    public PageInfo<Group> findGroupByManagerId(GroupQuery groupQuery){
        Integer managerId = groupQuery.getManagerId();
        AssertUtil.isTrue(managerId==null || managerId<=0, "请输入管理员id");
        User u = userMapper.selectByPrimaryKey(managerId);
        AssertUtil.isTrue(u == null, "该管理员不存在");

        PageHelper.startPage(groupQuery.getPage(), groupQuery.getLimit());
        List<Group> list = groupMapper.findGroupByManagerId(managerId);
        return new PageInfo<Group>(list);
    }

    /**
     * 查询userId管理的所有组
     * @param managerId
     * @return
     */
    public List<Group> queryMyGroupAll(Integer managerId){
        AssertUtil.isTrue(managerId==null || managerId<=0, "请输入管理员id");
        User u = userMapper.selectByPrimaryKey(managerId);
        AssertUtil.isTrue(u == null, "该管理员不存在");
        return groupMapper.findGroupByManagerId(managerId);
    }


    /**
     * 根据用户id，分页查找用户加入的组
     * @return
     */
    public PageInfo<Group> findUserJoinedGroup(GroupQuery groupQuery){
        Integer userId = groupQuery.getUserId();
        AssertUtil.isTrue(userId==null || userId<=0, "请输入用户id");
        User u = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(u == null, "该用户不存在");
        PageHelper.startPage(groupQuery.getPage(), groupQuery.getLimit());

        List<Group> list = groupMapper.findUserJoinedGroup(userId);
        return new PageInfo<Group>(list);
    }

    /**
     * 查找用户加入的全部组信息
     * @param userId
     * @return
     */
    public List<Group> findUserJoinedGroupAll(Integer userId){
        User u = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(u == null, "该用户不存在");
        return groupMapper.findUserJoinedGroup(userId);
    }

    /**
     * 移除小组
     * @param managerId
     * @param groupId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public String dissolveGroup(Integer managerId, Integer groupId) {
        AssertUtil.isTrue(managerId==null, "请求缺少管理员id");
        AssertUtil.isTrue(groupId == null, "请求缺少小组id");
        Group group = groupMapper.selectByPrimaryKey(groupId);
        AssertUtil.isTrue(group == null, "没有该小组的信息");
        AssertUtil.isTrue(managerId!=group.getManagerId(), "只有小组管理员才能解散小组");
        AssertUtil.isTrue(groupMapper.deleteByPrimaryKey(groupId)<1, "解散小组失败");
        return group.getGroupName();
    }
}
