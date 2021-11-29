package org.xm06.pms.dao;

import org.apache.ibatis.annotations.Param;
import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.Conference;
import org.xm06.pms.vo.Task;

import java.util.List;

public interface ConferenceMapper extends BaseMapper<Conference, Integer> {

    /**
     * 根据项目名称和小组id查询，，
     * （主要用于判断小组是否创建了具有相同项目名的项目）
     * @param conferenceName
     * @param creatorId
     * @return
     */
    Conference queryByConferenceNameAndCreatorId(@Param("conferenceName") String conferenceName,
                                           @Param("creatorId") Integer creatorId);

    /**
     * 根据小组id查找任会议
     * @param groupId
     * @return
     */
    List<Conference> queryGroupConference(Integer groupId);

    /**
     * 查找用户发布的会议
     * @param userId
     * @return
     */
    List<Conference> queryUserCreateConference(Integer userId);

    /**
     * 查找正在进行的会议
     * @param userId
     * @return
     */
    List<Conference> queryComingConference(Integer userId);

    /**
     * 查找结束的会议
     * @param userId
     * @return
     */
    List<Conference> queryFinishConference(Integer userId);

    /**
     * 查询全部的会议
     * @param userId
     * @return
     */
    List<Conference> queryAllConference(Integer userId);
}
