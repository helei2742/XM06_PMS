package org.xm06.pms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xm06.pms.dao.ConferenceMapper;
import org.xm06.pms.dao.GroupMapper;
import org.xm06.pms.dao.UserMapper;
import org.xm06.pms.query.ConferenceQuery;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.vo.Conference;
import org.xm06.pms.vo.Group;
import org.xm06.pms.vo.User;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConferenceService {

    @Resource
    private ConferenceMapper conferenceMapper;
    @Resource
    private GroupMapper groupMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 添加会议
     * @param conference
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addConference(Conference conference){
        AssertUtil.isTrue(StringUtils.isBlank(conference.getConferenceName()), "会议名不能为空");

        AssertUtil.isTrue(conference.getConferenceDate() == null, "会议日期不能为空");

        Group group = groupMapper.selectByPrimaryKey(conference.getGroupId());
        AssertUtil.isTrue(group == null, "不存在该小组");

        AssertUtil.isTrue(conference.getCreatorId() == null, "创建人id不能为空");

        User user = userMapper.selectByPrimaryKey(conference.getCreatorId());
        AssertUtil.isTrue(user == null, "创建者不存在");

        conference.setCreateDate(new Date());

        AssertUtil.isTrue(conferenceMapper.queryByConferenceNameAndCreatorId(
                conference.getConferenceName(), conference.getCreatorId())!=null,
                "您已创建了相同名称的会议");

        AssertUtil.isTrue(conferenceMapper.insertSelective(conference)<1,"创建会议失败");
    }

    /**
     * 删除会议方法
     * @param conferenceId
     * @param userId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteConference(Integer conferenceId, Integer userId) {
        AssertUtil.isTrue(conferenceId == null, "找不到该会议，conferenceId为空");
        AssertUtil.isTrue(userId == null, "userId为空");

        AssertUtil.isTrue( (conferenceMapper.selectByPrimaryKey(conferenceId)).getCreatorId() != userId, "该操作者不能删除该会议");

        AssertUtil.isTrue(conferenceMapper.deleteByPrimaryKey(conferenceId)<=0, "删除会议失败");
    }

    /**
     * 修改会议方法
     * @param conference
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyConference(Conference conference, Integer userId){
        AssertUtil.isTrue(StringUtils.isBlank(conference.getConferenceName()), "会议名不能为空");

        AssertUtil.isTrue(conference.getConferenceDate() == null, "会议日期不能为空");

        Group group = groupMapper.selectByPrimaryKey(conference.getGroupId());
        AssertUtil.isTrue(group == null, "不存在该小组");

        AssertUtil.isTrue(userId == null, "修改人id不能为空");

        User user = userMapper.selectByPrimaryKey(conference.getCreatorId());
        AssertUtil.isTrue(user == null, "修改人不存在");

        AssertUtil.isTrue( user.getId() != userId, "该操作者不能修改该会议");

        conference.setCreateDate(new Date());

        AssertUtil.isTrue(conferenceMapper.updateByPrimaryKeySelective(conference)<=0,"修改会议失败");
    }

    /**
     * 根据type 分类查询不同条件下的会议
     * @param conferenceQuery
     * conferenceQuery 中
     *      type 为 1、查询用户创建会议
     *             2.查询小组的会议
     *             3.查询还没开始的会议
     *             4，查询已经结束的会议
     *
     * @return
     */
    public List<Conference> pageQueryAllConference(ConferenceQuery conferenceQuery){
        Integer type = conferenceQuery.getType();

        AssertUtil.isTrue(type == null, "必须传入参数type");
//        User user = userMapper.selectByPrimaryKey(conferenceQuery.getCreatorId());
//        AssertUtil.isTrue(user == null, "该用户不存在");

        List<Conference> all;

        if (type.equals(ConferenceQuery.PAGEQUERYCONFERENCEBYGROUPID) && type.equals(ConferenceQuery.PAGEQUERYCONFERENCEBYNAMEANDGROUPID)
        && type.equals(ConferenceQuery.PAGEQUERYCONFERENCEBYALL)) {
            Group group = groupMapper.selectByPrimaryKey(conferenceQuery.getGroupId());
            AssertUtil.isTrue(group == null, "小组不存在");
        }
        if (type.equals(ConferenceQuery.PAGEQUERYCONFERENCEBYUSERID)) {
            // 1表示查询用户创建会议
            all = conferenceMapper.queryUserCreateConference(conferenceQuery.getCreatorId());
        } else if (type.equals(ConferenceQuery.PAGEQUERYCOMINGCONFERENCE)) {
            // 2表示查询还未开始的会议
            all = conferenceMapper.queryComingConference();
        } else if (type.equals(ConferenceQuery.PAGEQUERYFINISHCONFERENCE)) {
            // 3表示查询已经结束的会议
            all = conferenceMapper.queryFinishConference();
        } else if (type.equals(ConferenceQuery.PAGEQUERYCONFERENCEBYGROUPID)) {
            // 4表示查询小组会议
            all = conferenceMapper.queryGroupConference(conferenceQuery.getGroupId());
        } else if (type.equals(ConferenceQuery.PAGEQUERYCONFERENCEBYNAME)) {
            // 5表示查询会议名字关键字
            all = conferenceMapper.queryConferenceByName(conferenceQuery.getConferenceName());
        } else if (type.equals(ConferenceQuery.PAGEQUERYCONFERENCEBYNAMEANDCREATORID)) {
            // 6表示查询会议名字关键字和创建人ID
            System.out.println(conferenceMapper.queryByConferenceNameAndCreatorId(conferenceQuery.getConferenceName(),
                    conferenceQuery.getCreatorId()));
            all = new ArrayList<Conference>();
            all.add(conferenceMapper.queryByConferenceNameAndCreatorId(conferenceQuery.getConferenceName(),
                    conferenceQuery.getCreatorId()));
        } else if (type.equals(ConferenceQuery.PAGEQUERYCONFERENCEBYNAMEANDGROUPID)) {
            // 7表示查询会议名字关键字和小组ID
            all = conferenceMapper.queryGroupConferenceByName(conferenceQuery.getConferenceName(), conferenceQuery.getGroupId());
        } else if (type.equals(ConferenceQuery.PAGEQUERYCONFERENCEBYALL)) {
            // 8表示全条件查询会议
            all = conferenceMapper.queryConferenceByAll(conferenceQuery.getConferenceName(), conferenceQuery.getGroupId(),
                    conferenceQuery.getCreatorId());
        }  else if (type.equals(ConferenceQuery.PAGEQUERYCONFERENCEBYCREATORIDANDGROUPID)) {
            // 9表示按照小组ID和创建人ID查询会议
            all = conferenceMapper.queryConferenceByGroupIdAndCreatorId(conferenceQuery.getGroupId(), conferenceQuery.getCreatorId());
        } else {
            // 查询全部任务
            all = conferenceMapper.queryAllConference();
        }
        return all;
    }

}
