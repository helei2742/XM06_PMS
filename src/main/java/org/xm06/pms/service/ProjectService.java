package org.xm06.pms.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.xm06.pms.base.BaseService;
import org.xm06.pms.dao.GroupMapper;
import org.xm06.pms.dao.ProjectMapper;
import org.xm06.pms.dao.UserMapper;
import org.xm06.pms.query.ProjectQuery;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.vo.Group;
import org.xm06.pms.vo.Project;
import org.xm06.pms.vo.User;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService{

    @Resource
    ProjectMapper projectMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    GroupMapper groupMapper;


    /**
     * 创建项目方法
     * @param project
     */
    public void addProject(Project project){
        AssertUtil.isTrue(StringUtils.isBlank(project.getProjectName()), "项目名称不能为空");

        AssertUtil.isTrue(project.getGroupId() == null, "项目所属小组不能为空");
        AssertUtil.isTrue(project.getCreatorId() == null, "创建人id不能为空");

        User user = userMapper.selectByPrimaryKey(project.getCreatorId());
        AssertUtil.isTrue(user == null, "创建用户不存在");

        Group group = groupMapper.selectByPrimaryKey(project.getGroupId());
        AssertUtil.isTrue(group == null, "项目所属小组不存在");

        AssertUtil.isTrue(
                projectMapper.queryByProjectNameAndGroupId
                        (project.getProjectName(), project.getGroupId())!=null,
                "该组已经存在相同名称的项目");

        project.setCreateDate(new Date());
        project.setUpdateDate(new Date());
        project.setCompletionDegree(0.0);
        project.setIsValid(true);

        AssertUtil.isTrue(projectMapper.insertSelective(project)<=0, "创建项目失败");
    }

    /**
     * 删除项目方法
     * @param projectId
     * @param groupId
     * @param userId
     */
    public void deleteProject(Integer projectId, Integer groupId, Integer userId){
        AssertUtil.isTrue(projectId == null, "找不到该项目，projectId为空");
        AssertUtil.isTrue(groupId == null, "groupId为空");
        AssertUtil.isTrue(userId == null, "userId为空");

        Group group = groupMapper.selectByPrimaryKey(groupId);
        AssertUtil.isTrue(group == null, "该小组不存在");
        AssertUtil.isTrue(group.getManagerId() != userId, "该操作者不能删除小组项目");

        AssertUtil.isTrue(projectMapper.deleteByPrimaryKey(projectId)<=0, "删除项目失败");
    }

    /**
     * 分页查询 小组中项目的方法
     * @param projectQuery
     * @return
     */
    public PageInfo<Project> pageQueryGroupProject(ProjectQuery projectQuery){
        AssertUtil.isTrue(projectQuery.getGroupId() == null, "小组id为空");

        PageHelper.startPage(projectQuery.getPage(), projectQuery.getLimit());
        List<Project> all = projectMapper.queryByGroupId(projectQuery.getGroupId());
        return new PageInfo<>(all);
    }

    /**
     * 根据创建者（userId）的id分页查询项目
     * @param projectQuery
     * @return
     */
    public PageInfo<Project> pageQueryProjectByUserId(ProjectQuery projectQuery){
        AssertUtil.isTrue(projectQuery.getCreatorId() == null, "创建者id为空");

        PageHelper.startPage(projectQuery.getPage(), projectQuery.getLimit());
        List<Project> all = projectMapper.queryByCreatorId(projectQuery.getCreatorId());
        return new PageInfo<>(all);
    }

}
