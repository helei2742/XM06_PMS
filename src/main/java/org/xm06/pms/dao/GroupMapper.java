package org.xm06.pms.dao;

import org.apache.ibatis.annotations.Param;
import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.Group;

import java.util.List;

public interface GroupMapper extends BaseMapper<Group, Integer> {

    public Integer totalCount();
    /**
     * 根据用户id 和 小组id，将其添加到组中
     * @param userId
     * @param groupId
     * @return
     */
    public Integer userAddInGroup(@Param("userId") Integer userId,@Param("groupId") Integer groupId);

    /**
     * 根据用户id和小组id， 将用户移除小组
     * @param userId
     * @param groupId
     * @return
     */
    public Integer removeMember(@Param("userId") Integer userId,@Param("groupId") Integer groupId);

    /**
     * 根据组名查找组id
     * @param groupName
     * @return
     */
    public Integer queryIdByName(String groupName);

    /**
     * 根据组名查找小组，包含成员信息
     * @param groupName
     * @return
     */
    public Group findGroupByGroupName(String groupName);

    /**
     * 根据小组id查找小组全部信息，包含成员信息
     * @param id
     * @return
     */
    public Group findGroupAllInfoById(Integer id);

    /**
     * 查找所有队伍信息，包含队伍的成员信息
     * @return
     */
    public List<Group> findAll();

    /**
     * 根据管理员id查找全部小组信息,包含组员信息
     * @param managerId
     * @return
     */
    public List<Group> findGroupByManagerId(Integer managerId);

    /**
     * 查找用户管理的全部小组，不包含组员信息
     * @param managerId
     * @return
     */
    public List<Group> findGroupByManagerIdNoMember(Integer managerId);

    /**
     * 根据用户id查找加入的所有组,包含组员信息
     * @param userId
     * @return
     */
    public List<Group> findUserJoinedGroup(Integer userId);

    /**
     * 根据小组id查找成员id列表
     * @param groupId
     * @return
     */
    List<Integer> queryMemberIdList(Integer groupId);
}