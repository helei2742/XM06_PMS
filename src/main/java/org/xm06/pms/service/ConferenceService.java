package org.xm06.pms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
                "您已创建了相同名称的项目");

        AssertUtil.isTrue(conferenceMapper.insertSelective(conference)<1,"创建任务失败");
    }

    /**
     * 删除项目方法
     * @param conferenceId
     * @param groupId
     * @param userId
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteConference(Integer conferenceId, Integer groupId, Integer userId) {
        AssertUtil.isTrue(conferenceId == null, "找不到该会议，conferenceId为空");
        AssertUtil.isTrue(groupId == null, "groupId为空");
        AssertUtil.isTrue(userId == null, "userId为空");

        Group group = groupMapper.selectByPrimaryKey(groupId);
        AssertUtil.isTrue(group == null, "该会议不存在");
        AssertUtil.isTrue(group.getManagerId() != userId, "该操作者不能删除小组会议");

        AssertUtil.isTrue(conferenceMapper.deleteByPrimaryKey(conferenceId)<=0, "删除会议失败");
    }

    /**
     * 根据type 分类查询不同条件下的会议
     * @param conferenceQuery
     * conferenceQuery 中
     *      type 为 1、会议名称模糊查询）同时需传入其他必须参数(对应2.userId, 1.conferenceName)
     *             2.查询小组的会议
     *             3.查询还没开始的会议
     *             4，查询已经结束的会议
     *
     * @return
     */
    public PageInfo<Conference> pageQueryAllConference(ConferenceQuery conferenceQuery){
        Integer type = conferenceQuery.getType();

        AssertUtil.isTrue(type == null, "必须传入参数type");
//        User user = userMapper.selectByPrimaryKey(conferenceQuery.getCreatorId());
//        AssertUtil.isTrue(user == null, "该用户不存在");

        PageHelper.startPage(conferenceQuery.getPage(), conferenceQuery.getLimit());
        List<Conference> all = null;

        PageInfo<Conference> pageInfo = null;
        if (type == ConferenceQuery.PAGEQUERYCONFERENCEBYGROUPID) {
            Group group = groupMapper.selectByPrimaryKey(conferenceQuery.getGroupId());
            AssertUtil.isTrue(group == null, "小组不存在");
        }
        if (type == ConferenceQuery.PAGEQUERYCONFERENCEBYUSERID) {
            // 1表示查询用户创建会议
            all = conferenceMapper.queryUserCreateConference(conferenceQuery.getCreatorId());
        } else if (type == ConferenceQuery.PAGEQUERYCOMINGCONFERENCE) {
            // 2表示查询还未开始的会议
            all = conferenceMapper.queryComingConference(conferenceQuery.getCreatorId());
        } else if (type == ConferenceQuery.PAGEQUERYFINISHCONFERENCE) {
            // 3表示查询已经结束的会议
            all = conferenceMapper.queryFinishConference(conferenceQuery.getCreatorId());
        } else if (type == ConferenceQuery.PAGEQUERYCONFERENCEBYGROUPID) {
            // 4表示查询小组会议
            all = conferenceMapper.queryGroupConference(conferenceQuery.getGroupId());
        } else {
            // 查询全部任务
            all = conferenceMapper.queryAllConference(conferenceQuery.getCreatorId());
        }
        return new PageInfo<>(all);
    }

}
