package org.xm06.pms.dao;

import org.apache.ibatis.annotations.Param;
import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.ProjectUpdateRecord;

import java.util.List;

public interface ProjectUpdateRecordMapper extends BaseMapper<ProjectUpdateRecord, Integer> {

    /**
     * 查提交记录，默认时间降序
     * @param projectId
     * @return
     */
    List<ProjectUpdateRecord> queryUpdateRecord(Integer projectId);

    /**
     * 查询用户在一项目的总提交进度
     * @param userId
     * @param projectId
     * @return
     */
    Double queryUserTotalDegree(@Param("userId") Integer userId, @Param("projectId") Integer projectId);


    /**
     * 查询用户在某一项目中14天内的提交记录，
     * 实际指挥在ProjectUpdateRecord 对象中传入 submitDegree 和 createDate 属性
     * 作为当日的总提交量和日期
     * @param userId
     * @param projectId
     * @return
     */
    List<ProjectUpdateRecord> queryUserSubmit14day(@Param("userId") Integer userId, @Param("projectId") Integer projectId);
}