package org.xm06.pms.dao;

import org.xm06.pms.base.BaseMapper;
import org.xm06.pms.vo.Task;

import java.util.List;

public interface TaskMapper extends BaseMapper<Task, Integer> {


    public Task selectByTaskName(String taskName);

    /**
     * 根据小组id查找任务
     * @param groupId
     * @return
     */
    List<Task> queryGroupTask(Integer groupId);

    /**
     * 根据用户id查找用户全部需要完成的任务
     * @param userId
     * @return
     */
    List<Task> queryUserTask(Integer userId);

    /**
     * 查找用户发布的任务
     * @param userId
     * @return
     */
    List<Task> queryUserCreatTask(Integer userId);

    /**
     * 查找正在进行的任务
     * @param userId
     * @return
     */
    List<Task> queryDoingTask(Integer userId);

    /**
     * 查找已提交的任务
     * @param userId
     * @return
     */
    List<Task> querySubmitTask(Integer userId);

    /**
     * 查找过期的任务
     * @param userId
     * @return
     */
    List<Task> queryOutDateTask(Integer userId);

    /**
     * 查询没有提交的任务
     * @param userId
     * @return
     */
    List<Task> queryNotSubmitTask(Integer userId);
}