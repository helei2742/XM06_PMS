package org.xm06.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.query.TaskQuery;
import org.xm06.pms.query.TaskRecordQuery;
import org.xm06.pms.service.TaskService;
import org.xm06.pms.vo.Task;
import org.xm06.pms.vo.TaskRecord;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/task")
@Api(value = "TaskController", tags = "任务模块接口")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/queryByTaskId",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("根据taskId，查询任务的信息")
    @ApiImplicitParam(name="taskId",value="任务的主键id",required = true,dataTypeClass = Integer.class)
    public ResultInfo queryByTaskId(Integer taskId) {
        Task t = taskService.queryByTaskId(taskId);
        return success("", 200, t);
    }

    @PostMapping(value = "/add", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "发布任务接口",notes = "应传入：taskName，deadline，groupId，creatorId，description")
    public ResultInfo addTask(@RequestBody @Valid Task task) {
        taskService.addTask(task);
        return success("发布任务成功", 200, null);
    }

    @PostMapping(value = "/queryGroupTask", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查询小组的任务",notes = "需包含，groupId, 还可传入非必须的page,limit")
    public ResultInfo pageQueryGroupTask(@RequestBody @Valid TaskQuery taskQuery) {
        PageInfo<Task> tasks = taskService.queryGroupTask(taskQuery);
        return success("查询小组任务成功", 200, tasks);
    }


    @PostMapping(value = "/pageQueryUserTask", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查询用户接口",notes = "该方法包含了多种查询方式，type类型：（2.查询用户创建的任务，3.查询还没过期的任务，" +
            "4，查询用户已经提交过的任务，5.查询过期的任务、6。查看小组的任务，7、查看未提交的任务, 1、或其他，查看所有任务)" +
            "对应的其他需要的参数有（1或其他.userId, 2.userId,3.userId,4.userId,5.userId,6.groupId,7.userId")
    public ResultInfo pageQueryUserTask(@RequestBody @Valid TaskQuery taskQuery) {
        PageInfo<Task> tasks = taskService.pageQueryUserTask(taskQuery);
        return success("查询用户任务成功", 200, tasks);
    }

    @RequestMapping(value = "/submitTask", produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("用户提交任务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId",value = "任务id",required = true,dataTypeClass = Integer.class),
            @ApiImplicitParam(name="userId", value = "用户id",required = true,dataTypeClass = Integer.class),
            @ApiImplicitParam(name="description", value = "提交描述",dataTypeClass = String.class),
            @ApiImplicitParam(name="file",value = "提交的附件")
    })
    public ResultInfo submitTask(MultipartFile file, Integer userId,
                                 Integer taskId, String description
            , HttpServletRequest request) throws UnsupportedEncodingException {

        taskService.submitTask(file, userId, taskId, description);
        return success("提交成功", 200, null);
    }

    @PostMapping(value = "/userSubmitRecord", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查询用户所有提交记录接口",notes = "需包含userId, 还可选择传入page,limit")
    public ResultInfo userSubmitRecord(@RequestBody @Valid TaskRecordQuery taskRecordQuery) {
        PageHelper.startPage(taskRecordQuery.getPage(), taskRecordQuery.getLimit());
        List<TaskRecord> all = taskService.userTaskRecord(taskRecordQuery.getUserId());
        return success("查询用户所有提交记录成功", 200, new PageInfo<>(all));
    }


    @PostMapping(value = "/taskSubmitRecord", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查询任务的所有提交记录接口",notes = "需包含taskId, 还可选择传入page,limit")
    public ResultInfo taskSubmitRecord(@RequestBody @Valid TaskRecordQuery taskRecordQuery) {
        PageInfo<TaskRecord> all = taskService.taskRecord(taskRecordQuery);
        return success("查询任务提交记录成功", 200, all);
    }


    @PostMapping(value = "/userRecordOfTask", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查阅用户在某任务中的提交记录接口",notes = "需包含userId,taskId, 还可选择传入page,limit")
    public ResultInfo userRecordOfTask(@RequestBody @Valid TaskRecordQuery taskRecordQuery) {
        PageInfo<TaskRecord> all = taskService.userRecordOfTask(taskRecordQuery);
        return success("查阅用户提交记录成功", 200, all);
    }
}