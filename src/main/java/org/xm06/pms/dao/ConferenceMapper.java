package org.xm06.pms.dao;

import org.apache.ibatis.annotations.Param;
import org.xm06.pms.vo.Conference;

import java.util.List;

public interface ConferenceMapper {

    Conference queryByConferenceNameAndCreatorId(@Param("conferenceName") String conferenceName,
                                                 @Param("creatorId") Integer creatorId);

    /**
     * 根据小组id查找任会议
     * @param groupId
     * @return
     */
    List<Conference> queryGroupConference(Integer groupId);

    /**
     * 根据会议名字关键字和小组id查找任会议
     * @param conferenceName,groupId
     * @return
     */
    List<Conference> queryGroupConferenceByName(String conferenceName, Integer groupId);

    /**
     * 按照小组ID和创建人ID查询会议
     * @param groupId,creatorId
     * @return
     */
    List<Conference> queryConferenceByGroupIdAndCreatorId(Integer groupId, Integer creatorId);

    /**
     * 全条件查询会议
     * @param conferenceName,groupId,creatorId
     * @return
     */
    List<Conference> queryConferenceByAll(String conferenceName, Integer groupId, Integer creatorId);

    /**
     * 根据名字查找任会议
     * @param conferenceName
     * @return
     */
    List<Conference> queryConferenceByName(String conferenceName);

    /**
     * 查找用户发布的会议
     * @param creatorId
     * @return
     */
    List<Conference> queryUserCreateConference(Integer creatorId);

    /**
     * 查找正在进行的会议
     * @return
     */
    List<Conference> queryComingConference();

    /**
     * 查找结束的会议
     * @return
     */
    List<Conference> queryFinishConference();

    /**
     * 查询全部的会议
     * @return
     */
    List<Conference> queryAllConference();


    int deleteByPrimaryKey(Integer id);

    int insert(Conference record);

    int insertSelective(Conference record);

    Conference selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Conference record);

    int updateByPrimaryKey(Conference record);
}