package org.xm06.pms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xm06.pms.dao.GroupMapper;
import org.xm06.pms.dao.TaskMapper;
import org.xm06.pms.dao.TaskRecordMapper;
import org.xm06.pms.dao.UserMapper;
import org.xm06.pms.exception.FileSaveException;
import org.xm06.pms.query.TaskQuery;
import org.xm06.pms.query.TaskRecordQuery;
import org.xm06.pms.utils.AssertUtil;
import org.xm06.pms.utils.FileUtil;
import org.xm06.pms.utils.Md5Util;
import org.xm06.pms.vo.Group;
import org.xm06.pms.vo.Task;
import org.xm06.pms.vo.TaskRecord;
import org.xm06.pms.vo.User;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service
public class TaskService{
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private GroupMapper groupMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TaskRecordMapper taskRecordMapper;

    /**
     * 添加任务
     * @param task
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addTask(Task task){
        AssertUtil.isTrue(StringUtils.isBlank(task.getTaskName()), "任务名不能为空");
        AssertUtil.isTrue(task.getDeadline() == null, "截至日期不能为空");
        Group group = groupMapper.selectByPrimaryKey(task.getGroupId());
        AssertUtil.isTrue(group == null, "不存在该小组");
        User user = userMapper.selectByPrimaryKey(task.getCreatorId());
        AssertUtil.isTrue(user == null, "创建者不存在");
        task.setCreateDate(new Date());
        task.setIsValid(true);
        AssertUtil.isTrue(taskMapper.insertSelective(task)<1,"创建任务失败");
    }

    /**
     * 分页查询小组中的任务
     * @param taskQuery
     * @return
     */
    public PageInfo<Task> queryGroupTask(TaskQuery taskQuery) {
        Group group = groupMapper.selectByPrimaryKey(taskQuery.getGroupId());
        AssertUtil.isTrue(group == null, "不存在该小组");
        PageHelper.startPage(taskQuery.getPage(), taskQuery.getLimit());
        List<Task> all = taskMapper.queryGroupTask(taskQuery.getGroupId());
        return new PageInfo<>(all);
    }

    /**
     * 分页查询用户的任务
     * @param taskQuery
     * @return
     */
    public PageInfo<Task> pageQueryUserTask(TaskQuery taskQuery) {
        User user = userMapper.selectByPrimaryKey(taskQuery.getUserId());
        AssertUtil.isTrue(user == null, "该用户不存在");

        Integer type = taskQuery.getType();
        if(type == 6) {
            Group group = groupMapper.selectByPrimaryKey(taskQuery.getGroupId());
            AssertUtil.isTrue(group == null, "小组不存在");
        }

        PageHelper.startPage(taskQuery.getPage(), taskQuery.getLimit());
        List<Task> all = null;

        if(type == 2){
            //查询用户创建的任务
            all = taskMapper.queryUserCreatTask(taskQuery.getUserId());
        }else if(type == 3){
            //查询还没过期的任务
            all = taskMapper.queryDoingTask(taskQuery.getUserId());
        }else if(type == 4){
            //查询用户已经提交过的任务
            all = taskMapper.querySubmitTask(taskQuery.getUserId());
        }else if(type == 5){
            //查询过期的任务
            all = taskMapper.queryOutDateTask(taskQuery.getUserId());
        }else if(type == 6){
            //查看小组的任务
            all = taskMapper.queryGroupTask(taskQuery.getGroupId());
        }else if(type == 7){
            all = taskMapper.queryNotSubmitTask(taskQuery.getUserId());
        }
        else{
            //查询全部任务
            all = taskMapper.queryUserTask(taskQuery.getUserId());
        }
        return new PageInfo<>(all);
    }


    /**
     * 提交任务
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void submitTask(MultipartFile file, Integer userId, Integer taskId, String description) {
        User user = userMapper.selectByPrimaryKey(userId);
        AssertUtil.isTrue(user == null, "没有该用户");
        Task task = taskMapper.selectByPrimaryKey(taskId);
        AssertUtil.isTrue(task == null, "不存在该任务");

        String url = null;
        if(file != null) {
            try {
                url = FileUtil.saveFile(file,
                        FileUtil.getUserUploadTaskFileSavePath(user.getUserName()));

            } catch (IOException e) {
                e.printStackTrace();
                throw new FileSaveException();
            }
        }
        System.out.println(url);
        TaskRecord taskRecord = new TaskRecord();
        taskRecord.setTaskId(taskId);
        taskRecord.setUserId(userId);
        taskRecord.setDescription(description);
        taskRecord.setFileUrl(url);
        taskRecord.setSubmitDate(new Date());
        System.out.println(taskRecord.getSubmitDate());
        AssertUtil.isTrue(taskRecordMapper.insertSelective(taskRecord)<1, "保存提交记录失败");
    }


    /**
     * 查询任务的所有提交记录
     * @param taskRecordQuery
     * @return
     */
    public PageInfo<TaskRecord> taskRecord(TaskRecordQuery taskRecordQuery) {
        PageHelper.startPage(taskRecordQuery.getPage(), taskRecordQuery.getLimit());
        List<TaskRecord> all = taskRecordMapper.selectByTaskId(taskRecordQuery.getTaskId());
        return new PageInfo<>(all);
    }

    /**
     * 查阅用户在某任务中的提交记录
     * @param taskRecordQuery
     * @return
     */
    public PageInfo<TaskRecord> userRecordOfTask(TaskRecordQuery taskRecordQuery){

        PageHelper.startPage(taskRecordQuery.getPage(), taskRecordQuery.getLimit());
        List<TaskRecord> all = taskRecordMapper
                .selectUserRecordOfTask(taskRecordQuery.getUserId(), taskRecordQuery.getTaskId());
        return new PageInfo<>(all);
    }

    /**
     * 分页查询用户的所有提交记录
     * @param taskRecordQuery
     * @return
     */
    public PageInfo<TaskRecord> queryUserTaskRecord(TaskRecordQuery taskRecordQuery){
        User user = userMapper.selectByPrimaryKey(taskRecordQuery.getUserId());
        AssertUtil.isTrue(user == null, "没有该用户");
        PageHelper.startPage(taskRecordQuery.getPage(), taskRecordQuery.getLimit());
        List<TaskRecord> all = taskRecordMapper.queryUserTaskRecord(taskRecordQuery.getUserId());
        return new PageInfo<>(all);
    }

    /**
     * 根据taskId 查找任务
     * @param taskId
     * @return
     */
    public Task queryByTaskId(Integer taskId) {
        Task task = taskMapper.selectByPrimaryKey(taskId);
        AssertUtil.isTrue(task == null, "不存在该任务");
        User user = userMapper.selectByPrimaryKey(task.getCreatorId());
        AssertUtil.isTrue(user == null, "创建者已注销");
        user.setUserPwd("");
        task.setCreator(user);
        return task;
    }

    /**
     * 修改任务
     * @param task
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void alterTask(Task task) {
        taskMapper.updateByPrimaryKeySelective(task);
    }

    /**
     * 查询任务提交记录的echarts图表数据，
     * @param taskId
     * @return
     */
    public Map<String, Object> queryTaskSubmitCharts(Integer taskId) {
        Task task = taskMapper.selectByPrimaryKey(taskId);
        AssertUtil.isTrue(task == null, "该任务不存在");
        //获取任务 的成员
        Group group = groupMapper.findGroupAllInfoById(task.getGroupId());
        AssertUtil.isTrue(group==null, "任务所属小组已解散");

        List<User> memberList = group.getMemberList();

        List<User> submitUsers = new ArrayList<>();
        List<User> notSubmitUsers = new ArrayList<>();

        for (User user : memberList) {
            List<TaskRecord> records = taskRecordMapper.selectUserRecordOfTask(user.getId(), taskId);
            if(records!=null&&records.size()>0){
                //用户的updateDate 用作提交时间
                user.setUpdateDate(records.get(0).getSubmitDate());
                submitUsers.add(user);
            }else {
                notSubmitUsers.add(user);
            }
        }

        Map<String, Object> res = new HashMap<>();
        res.put("max", memberList.size());
        res.put("submitCount",submitUsers.size());
        res.put("notSubmitCount",notSubmitUsers.size());
        res.put("submitUsers", submitUsers);
        res.put("notSubmitUsers",notSubmitUsers);
        res.put("code", 200);
        return res;
    }
}
