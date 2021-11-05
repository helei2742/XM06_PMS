package org.xm06.pms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void addProject(Project project){
        AssertUtil.isTrue(StringUtils.isBlank(project.getProjectName()), "项目名称不能为空");

        AssertUtil.isTrue(project.getCreatorId() == null, "创建人id不能为空");

        User user = userMapper.selectByPrimaryKey(project.getCreatorId());
        AssertUtil.isTrue(user == null, "创建用户不存在");

        AssertUtil.isTrue(projectMapper.queryByProjectNameAndCreatorId(
                project.getProjectName(), project.getCreatorId())!=null,
                "您已创建了相同名称的项目");

        project.setCreateDate(new Date());
        project.setUpdateDate(new Date());
        project.setCompletionDegree(0.0);
        project.setIsValid(true);

        Integer f = projectMapper.insertHasKey(project);
        AssertUtil.isTrue(f == null || f<=0, "创建项目失败");

        /*
         * 添加项目小组
         */
        for (Integer id : project.getGroupIds()) {
            AssertUtil.isTrue(groupMapper.selectByPrimaryKey(id) == null,
                    "小组不存在或已失效");
            AssertUtil.isTrue(!addProjectGroupNoCheck(project.getId(), id),
                    "项目添加小组失败");
        }

    }


    /**
     * 添加参加项目的小组
     * @param projectId
     * @param groupId
     */
    public void addProjectGroup(Integer projectId, Integer groupId){
        AssertUtil.isTrue(projectMapper.selectByPrimaryKey(projectId) == null,
                "不存在该项目");
        AssertUtil.isTrue(groupMapper.selectByPrimaryKey(groupId) == null,
                "不存在该用户");
        AssertUtil.isTrue(!addProjectGroupNoCheck(projectId, groupId), "项目添加小组失败");
    }

    /**
     * 不进行参数校验添加项目小组
     * @param projectId
     * @param groupId
     * @return 添加成功返回true，否则false
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addProjectGroupNoCheck(Integer projectId, Integer groupId){
        return projectMapper.addProjectGroup(projectId, groupId) > 0;
    }


    /**
     * 删除项目方法
     * @param projectId
     * @param groupId
     * @param userId
     */
    @Transactional(propagation = Propagation.REQUIRED)
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
     * 根据查询的type， 返回不同查询结果， 包含分页，
     * @param projectQuery
     *      projectQuery 中
     *      type 为 1  代表查找用户创建的项目
     *             2   代表查找用户加入小组的项目
     *             3   代表查找所有公开的项目
     * @return
     */
    public PageInfo<Project> queryProjectList(ProjectQuery projectQuery){
        Integer type = projectQuery.getType();
        AssertUtil.isTrue(type == null, "必须传入参数type");

        PageInfo<Project> pageInfo = null;
        if(type == ProjectQuery.PAGEQUERYPROJECTBYUSERID){
            //1代表查找用户创建的项目
            pageInfo = pageQueryProjectByUserId(projectQuery);
        }else if (type == ProjectQuery.PAGEQUERYUSERJOINEDGROUPPROJECT){
            //2代表查找用户加入小组的项目
            pageInfo = pageQueryUserJoinedGroupProject(projectQuery);
        }else if(type == ProjectQuery.PAGEQUERYPUBLICPROJECT){
            //3 代表查找所有公开的项目
            pageInfo = pageQueryPublicProject(projectQuery);
        }else {
            AssertUtil.isTrue(true, "没有该type的查询");
        }
        return pageInfo;
    }


    /**
     * 分页查询用户加入小组中的项目
     * @param projectQuery
     * @return
     */
    public PageInfo<Project> pageQueryUserJoinedGroupProject(ProjectQuery projectQuery){
        Integer userId = projectQuery.getUserId();
        AssertUtil.isTrue(userId == null, "用户id为空");
        AssertUtil.isTrue(
                userMapper.selectByPrimaryKey(userId)==null,
                "不存在该用户或已失效");

        PageHelper.startPage(projectQuery.getPage(), projectQuery.getLimit());


        List<Project> all = this.userJoinedGroupProjectOrderTypeHandle(
                projectQuery.getOrderType(),userId);
        return new PageInfo<>(all);
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
        Integer userId = projectQuery.getUserId();
        AssertUtil.isTrue(userId == null, "创建者id为空");

        PageHelper.startPage(projectQuery.getPage(), projectQuery.getLimit());


        List<Project> all = this.userProjectOrderTypeHandle(
                projectQuery.getOrderType(), userId);

        return new PageInfo<>(all);
    }

    /**
     * 分页查询公开项目
     * @return
     */
    public PageInfo<Project> pageQueryPublicProject(ProjectQuery projectQuery){
        PageHelper.startPage(projectQuery.getPage(), projectQuery.getLimit());

        Integer orderType = projectQuery.getOrderType();
        List<Project> all = this.publicProjectOrderTypeHandle(orderType);
        return new PageInfo<>(all);
    }


    /**
     * 根据orderType 分类查询不同排序条件下用户加入小组中的项目
     * @param orderType
     * @param userId
     * @return
     */
    private List<Project> userJoinedGroupProjectOrderTypeHandle(Integer orderType, Integer userId){
        if(orderType == ProjectQuery.CREATEDATEDESC){
            return projectMapper.queryUserJoinedGroupProject(userId);
        }else if(orderType == ProjectQuery.CREATEDATEASC){
            return projectMapper.queryUserJoinedGroupProjectCreateDateASC(userId);
        }else if(orderType == ProjectQuery.COMPLETIONDEGREEDESC){
            return projectMapper.queryUserJoinedGroupProjectCompletion_DESC(userId);
        }else if(orderType == ProjectQuery.COMPLETIONDEGREEASC){
            return projectMapper.queryUserJoinedGroupProjectCompletion_ASC(userId);
        }else {
            AssertUtil.isTrue(true, "没有该查询条件");
        }
        return null;
    }

    /**
     * 根据orderType 分类查询不同条件下 creatorId 的用户创建的项目
     * @param orderType
     * @return
     */
    private List<Project> userProjectOrderTypeHandle(Integer orderType, Integer creatorId) {
        if(orderType == ProjectQuery.CREATEDATEDESC){
            return projectMapper.queryByCreatorId(creatorId);
        }else if(orderType == ProjectQuery.CREATEDATEASC){
            return projectMapper.queryByCreatorIdCreateDateASC(creatorId);
        }else if(orderType == ProjectQuery.COMPLETIONDEGREEDESC){
            return projectMapper.queryByCreatorIdCompletionDESC(creatorId);
        }else if(orderType == ProjectQuery.COMPLETIONDEGREEASC){
            return projectMapper.queryByCreatorIdCompletionASC(creatorId);
        }else {
            AssertUtil.isTrue(true, "没有该查询条件");
        }
        return null;
    }
    /**
     * 根据ordetType 分类查询不同条件下的公共项目
     * @param orderType
     * @return
     */
    private List<Project> publicProjectOrderTypeHandle(Integer orderType){
        if(orderType == ProjectQuery.CREATEDATEDESC){
            return projectMapper.queryPublicProject();
        }else if(orderType == ProjectQuery.CREATEDATEASC){
            return projectMapper.queryPublicProjectCreateDateASC();
        }else if(orderType == ProjectQuery.COMPLETIONDEGREEDESC){
            return projectMapper.queryPublicProjectCompletionDegreeDESC();
        }else if(orderType == ProjectQuery.COMPLETIONDEGREEASC){
            return projectMapper.queryPublicProjectCompletionDegreeASC();
        }else {
            AssertUtil.isTrue(true, "没有该查询条件");
        }
        return null;
    }
}
