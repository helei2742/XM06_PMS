package org.xm06.pms.dao;

import org.apache.ibatis.annotations.Param;
import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.TaskRecord;

import java.util.List;

public interface TaskRecordMapper extends BaseMapper<TaskRecord, Integer> {

    /**
     * 查询用户的所有提交记录
     * @param userId
     * @return
     */
    List<TaskRecord> queryUserTaskRecord(Integer userId);

    /**
     * 查询任务的提交记录
     * @param taskId
     * @return
     */
    List<TaskRecord> selectByTaskId(Integer taskId);

    /**
     * 查询用户在某一任务中的提交记录
     * @param userId
     * @param taskId
     * @return
     */
    List<TaskRecord> selectUserRecordOfTask(@Param("userId") Integer userId,@Param("taskId") Integer taskId);


    /**
     * 删除id的记录
     * @param recordIds
     * @return
     */
    Integer deleteByIds(@Param("list") List<Integer> recordIds,@Param("userId") Integer userId);

}