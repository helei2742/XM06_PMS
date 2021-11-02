package org.xm06.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.query.TaskQuery;
import org.xm06.pms.query.TaskRecordQuery;
import org.xm06.pms.service.TaskService;
import org.xm06.pms.vo.Task;
import org.xm06.pms.vo.TaskRecord;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;


    @RequestMapping(value = "/add",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo addTask(Task task){
        taskService.addTask(task);
        return success("发布任务成功",200,null);
    }

    @RequestMapping(value = "/queryGroupTask",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo queryGroupTask(TaskQuery taskQuery){
        PageInfo<Task> tasks = taskService.queryGroupTask(taskQuery);
        return success("查询小组任务成功", 200, tasks);
    }

    @RequestMapping(value = "/pageQueryUserTask",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo pageQueryUserTask(TaskQuery taskQuery){
        PageInfo<Task> tasks = taskService.pageQueryUserTask(taskQuery);
        return success("查询用户任务成功", 200, tasks);
    }

    @RequestMapping(value = "/submitTask",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo submitTask(MultipartFile file, Integer userId,
                                 Integer taskId, String description
                                ,HttpServletRequest request) throws UnsupportedEncodingException {
        taskService.submitTask(file,userId,taskId,description);
        return success("提交成功", 200, null);
    }

    @RequestMapping(value = "/userSubmitRecord",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo userSubmitRecord(TaskRecordQuery taskRecordQuery){
        PageHelper.startPage(taskRecordQuery.getPage(), taskRecordQuery.getLimit());
        List<TaskRecord> all = taskService.userTaskRecord(taskRecordQuery.getUserId());
        return success("查询用户所有提交记录成功", 200, new PageInfo<>(all));
    }

    @RequestMapping(value = "/taskSubmitRecord",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo taskSubmitRecord(TaskRecordQuery taskRecordQuery){
        PageInfo<TaskRecord> all = taskService.taskRecord(taskRecordQuery);
        return success("查询任务提交记录成功", 200, all);
    }

    @RequestMapping(value = "/userRecordOfTask",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo userRecordOfTask(TaskRecordQuery taskRecordQuery){
        PageInfo<TaskRecord> all = taskService.userRecordOfTask(taskRecordQuery);
        return success("查阅用户提交记录成功", 200, all);
    }
}
