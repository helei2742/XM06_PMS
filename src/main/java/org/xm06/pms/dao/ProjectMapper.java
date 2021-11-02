package org.xm06.pms.dao;

import org.apache.ibatis.annotations.Param;
import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.Project;

import java.util.List;

public interface ProjectMapper extends BaseMapper<Project, Integer> {


    /**
     * 根据项目名称和小组id查询，，
     * （主要用于判断小组是否创建了具有相同项目名的项目）
     * @param projectName
     * @param groupId
     * @return
     */
    Project queryByProjectNameAndGroupId(@Param("projectName") String projectName,
                                         @Param("groupId") Integer groupId);

    /**
     * 根据小组id查询 任务
     * @param groupId
     * @return
     */
    List<Project> queryByGroupId(Integer groupId);


    /**
     * 根据创建者id查询任务
     * @param creatorId
     * @return
     */
    List<Project> queryByCreatorId(Integer creatorId);
}