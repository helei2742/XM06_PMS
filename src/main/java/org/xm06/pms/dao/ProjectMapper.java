package org.xm06.pms.dao;

import org.apache.ibatis.annotations.Param;
import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.Project;
import org.xm06.pms.vo.User;

import java.util.List;

public interface ProjectMapper extends BaseMapper<Project, Integer> {


    /**
     * 根据项目名称和小组id查询，，
     * （主要用于判断小组是否创建了具有相同项目名的项目）
     * @param projectName
     * @param creatorId
     * @return
     */
    Project queryByProjectNameAndCreatorId(@Param("projectName") String projectName,
                                         @Param("creatorId") Integer creatorId);

    /**
     *  小组加入项目
     * @param projectId
     * @param groupId
     */
    Integer addProjectGroup(@Param("projectId") Integer projectId,
                            @Param("groupId") Integer groupId);

    /**
     * 查找小组中的项目
     * @param groupId
     * @return
     */
    List<Project> queryByGroupId(Integer groupId);

    /**
     * 查找项目中的用户
     * @param projectId
     * @return
     */
    List<User> queryProjectUser(Integer projectId);

    /**
     * 根据项目名查找项目
     * @param projectName
     * @return
     */
    Project queryByProjectName(String projectName);

//===================== 查找用户加入小组中的项目 start=============
    /**
     * 根据用户id，查找用户加入小组中的项目，默认创建时间降序
     * @param userId
     * @return
     */
    List<Project>  queryUserJoinedGroupProject(Integer userId);

    /**
     * 根据用户id，查找用户加入小组中的项目，创建时间升序
     * @param userId
     * @return
     */
    List<Project>  queryUserJoinedGroupProjectCreateDateASC(Integer userId);

    /**
     * 根据用户id，查找用户加入小组中的项目，完成度降序
     * @param userId
     * @return
     */
    List<Project>  queryUserJoinedGroupProjectCompletion_DESC(Integer userId);

    /**
     * 根据用户id，查找用户加入小组中的项目，完成度升序
     * @param userId
     * @return
     */
    List<Project>  queryUserJoinedGroupProjectCompletion_ASC(Integer userId);

//==================== 查找用户加入小组中的项目 end===================


//==================== 查找用户创建的项目 start ==================
    /**
     * 根据创建者id查询任务， 默认创建时间降序
     * @param creatorId
     * @return
     */
    List<Project> queryByCreatorId(Integer creatorId);

    /**
     * 根据创建者id查询任务， 创建时间升序
     * @param creatorId
     * @return
     */
    List<Project> queryByCreatorIdCreateDateASC(Integer creatorId);

    /**
     * 根据创建者id查询任务， 完成度降序
     * @param creatorId
     * @return
     */
    List<Project> queryByCreatorIdCompletionDESC(Integer creatorId);

    /**
     * 根据创建者id查询任务， 完成度升序
     * @param creatorId
     * @return
     */
    List<Project> queryByCreatorIdCompletionASC(Integer creatorId);

//==================== 查找用户创建的项目   end ================


//=======================查询所有公开项目 start=================
    /**
     * 查询所有公开项目, 默认创建时间降序
     * @return
     */
    List<Project> queryPublicProject();

    /**
     * 查询所有公开项目, 创建时间升序
     * @return
     */
    List<Project> queryPublicProjectCreateDateASC();

    /**
     * 查询所有公开项目，完成度降序
     * @return
     */
    List<Project> queryPublicProjectCompletionDegreeDESC();

    /**
     * 查询所有公开项目，完成度升序
     * @return
     */
    List<Project> queryPublicProjectCompletionDegreeASC();
//=======================查询所有公开项目 end =================



//====================== 根据项目名，模糊查找项目 start ==========================

    /**
     * 根据项目名模糊查找项目，默认时间降序
     * @param likeName
     * @return
     */
    List<Project> queryNameLikeProject(String likeName);

    /**
     * 根据项目名模糊查找项目，时间升序
     * @param likeName
     * @return
     */
    List<Project> queryNameLikeProjectCreateDateASC(String likeName);
    /**
     * 根据项目名模糊查找项目，完成度降序
     * @param likeName
     * @return
     */
    List<Project> queryNameLikeProjectCompletion_DESC(String likeName);
    /**
     * 根据项目名模糊查找项目，完成度升序
     * @param likeName
     * @return
     */
    List<Project> queryNameLikeProjectCompletion_ASC(String likeName);

    /**
     * 删除参与项目的记录
     * @param groupId
     * @return
     */
    Integer deleteGroupInProject(Integer groupId);
//====================== 根据项目名，模糊查找项目 end =========================

}