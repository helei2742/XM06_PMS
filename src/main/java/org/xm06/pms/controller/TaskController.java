package org.xm06.pms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.model.ExcelConfig;
import org.xm06.pms.model.TaskRecordExport;
import org.xm06.pms.query.TaskQuery;
import org.xm06.pms.query.TaskRecordQuery;
import org.xm06.pms.service.TaskService;
import org.xm06.pms.utils.PoiUtils;
import org.xm06.pms.vo.Task;
import org.xm06.pms.vo.TaskRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/task")
@Api(value = "TaskController", tags = "任务模块接口")
@ApiSupport(author = "914577981@qq.com")
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
    @ApiOperationSupport(includeParameters = {"task.taskName","task.deadline","task.groupId","task.creatorId","task.description"})
    @ApiOperation(value = "发布任务接口",notes = "应传入：taskName，deadline，groupId，creatorId，description")
    public ResultInfo addTask(@RequestBody Task task) {
        taskService.addTask(task);
        //统计
        systemRecordService.addTaskCreateCount();
        return success("发布任务成功", 200, null);
    }


    @PostMapping(value = "/alterTask", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperationSupport(includeParameters = {"task.taskName","task.deadline","task.description"})
    @ApiOperation(value = "修改任务接口",notes = "应传入：taskName，deadline，description")
    public ResultInfo alterTask(@RequestBody Task task) {
        task.setCreateDate(null);
        task.setCreator(null);
        task.setGroupId(null);
        task.setCreatorId(null);
        task.setIsValid(null);
        taskService.alterTask(task);

        return success("发布任务成功", 200, null);
    }

    @PostMapping(value = "/dropTask", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "删除任务接口",notes = "应传入：taskId, creatorId,不会删除任务的提交记录")
    @ApiOperationSupport(includeParameters = {"task.taskId","task.creatorId"})
    public ResultInfo dropTask(@RequestBody Task task) {

        taskService.dropTask(task);

        return success("发布任务成功", 200, null);
    }

    @PostMapping(value = "/queryGroupTask", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查询小组的任务",notes = "需包含，groupId, 还可传入非必须的page,limit")
    @ApiOperationSupport(includeParameters = {"taskQuery.groupId","taskQuery.page","taskQuery.limit"})
    public ResultInfo pageQueryGroupTask(@RequestBody TaskQuery taskQuery) {
        PageInfo<Task> tasks = taskService.queryGroupTask(taskQuery);
        return success("查询小组任务成功", 200, tasks);
    }


    @PostMapping(value = "/pageQueryUserTask", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查询任务接口",notes = "该方法包含了多种查询方式，type类型：（2.查询用户创建的任务，3.查询还没过期的任务，" +
            "4，查询用户已经提交过的任务，5.查询过期的任务、6。查看小组的任务，7、查看未提交的任务, 1、或其他，查看所有任务)" +
            "对应的其他需要的参数有（1或其他.userId, 2.userId,3.userId,4.userId,5.userId,6.groupId,7.userId")
    @ApiOperationSupport(includeParameters = {"taskQuery.type","taskQuery.userId","taskQuery.groupId"})
    public ResultInfo pageQueryUserTask(@RequestBody TaskQuery taskQuery) {
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

        systemRecordService.addTaskSubmitCount();
        return success("提交成功", 200, null);
    }

    @PostMapping(value = "/userSubmitRecord", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查询用户所有提交记录接口",notes = "需包含userId, 还可选择传入page,limit")
    @ApiOperationSupport(includeParameters = {"taskRecordQuery.userId","taskRecordQuery.page","taskRecordQuery.limit"})
    public ResultInfo userSubmitRecord(@RequestBody TaskRecordQuery taskRecordQuery) {
        PageInfo<TaskRecord> info = taskService.queryUserTaskRecord(taskRecordQuery);
        return success("查询用户所有提交记录成功", 200, info);
    }


    @PostMapping(value = "/taskSubmitRecord", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查询任务的所有提交记录接口",notes = "需包含taskId, 还可选择传入page,limit")
    @ApiOperationSupport(includeParameters = {"taskRecordQuery.taskId","taskRecordQuery.page","taskRecordQuery.limit"})
    public ResultInfo taskSubmitRecord(@RequestBody TaskRecordQuery taskRecordQuery) {
        PageInfo<TaskRecord> all = taskService.taskRecord(taskRecordQuery);
        return success("查询任务提交记录成功", 200, all);
    }


    @PostMapping(value = "/userRecordOfTask", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查阅用户在某任务中的提交记录接口",notes = "需包含userId,taskId, 还可选择传入page,limit")
    @ApiOperationSupport(includeParameters = {"taskRecordQuery.userId","taskRecordQuery.taskId","taskRecordQuery.page","taskRecordQuery.limit"})
    public ResultInfo userRecordOfTask(@RequestBody TaskRecordQuery taskRecordQuery) {
        PageInfo<TaskRecord> all = taskService.userRecordOfTask(taskRecordQuery);
        return success("查阅用户提交记录成功", 200, all);
    }

    @PostMapping("/deleteTaskSubmitRecord")
    @ResponseBody
    @ApiOperation(value = "删除某一提交记录接口",notes = "需包含userId(用于校验身份）,recordId")
    public ResultInfo deleteTaskSubmitRecord(@RequestBody Map<String,String> map){
        Integer userId = Integer.parseInt(map.get("userId"));
        Integer recordId = Integer.parseInt(map.get("recordId"));
        taskService.deleteTaskSubmitRecord(userId, recordId);
        return success("删除成功", 200, null);
    }

    @PostMapping("/deleteSelectedRecord")
    @ResponseBody
    @ApiOperation(value = "删除recordIds数组中值作为主键的任务提交记录",notes = "需包含taskId,recordIds[]")
    public ResultInfo deleteSelectedRecord(@RequestBody Map<String,Object> map){
        List<Integer> recordIds = (List<Integer>) map.get("recordIds");
        Integer userId = (Integer) map.get("userId");
        taskService.deleteSelectedRecord(recordIds, userId);
        return success("删除选中成功", 200, null);
    }

    @PostMapping(value = "/queryTaskSubmitCharts")
    @ResponseBody
    @ApiOperation(value = "查询任务提交情况的echart表数据",notes = "需包含taskId")
    @ApiOperationSupport(includeParameters = {"taskRecordQuery.taskId"})
    public Map<String,Object> queryTaskSubmitCharts(@RequestBody TaskRecordQuery taskRecordQuery) {
        return taskService.queryTaskSubmitCharts(taskRecordQuery.getTaskId());
    }


    @PostMapping("/exportMyRecordExcel")
    @ResponseBody
    @ApiOperation(value = "下载用户所有任务提交记录excel表接口",notes = "userId")
    public ResponseEntity<byte[]> exportMyRecordExcel(@RequestBody Map<String,String> map
            , HttpServletResponse response) throws UnsupportedEncodingException {
        Integer userId = Integer.parseInt(map.get("userId"));

        List<TaskRecordExport> all = taskService.exportUserRecordExcel(userId);
        ExcelConfig excelConfig = new ExcelConfig();



        if(all.size() > 0){
            excelConfig.setManager(all.get(0).getSubmitUserName());
            excelConfig.setAuthor(all.get(0).getSubmitUserName());
            excelConfig.setSheetName("任务("+all.get(0).getTaskName()+")的提交记录");
        }
        excelConfig.setCategory("我的提交记录");
        excelConfig.setCompany("SCU");
        excelConfig.setSubject("提交记录表");
        excelConfig.setTitle("提交记录");
        excelConfig.setComments("备注信息暂无");

        response.setHeader("Access-Control-Expose-Headers", "filename");
        response.setHeader("filename", URLEncoder.encode("我的提交记录表.xls","utf-8"));
        return PoiUtils.exportExcel(all, excelConfig, TaskRecordExport.class);
    }

    @PostMapping("/exportTaskRecordExcel")
    @ResponseBody
    @ApiOperation(value = "下载任务的所有提交记录excel表接口",notes = "taskId")
    public ResponseEntity<byte[]> exportTaskRecordExcel(@RequestBody Map<String,String> map, HttpServletResponse response) throws UnsupportedEncodingException {
        Integer taskId = Integer.parseInt(map.get("taskId"));

        List<TaskRecordExport> all = taskService.exportTaskRecordExcel(taskId);
        ExcelConfig excelConfig = new ExcelConfig();

        if(all.size() > 0){
            excelConfig.setManager(all.get(0).getSubmitUserName());
            excelConfig.setAuthor(all.get(0).getSubmitUserName());
            excelConfig.setSheetName("任务("+all.get(0).getTaskName()+")的提交记录");
        }
        excelConfig.setCategory("任务的提交记录");
        excelConfig.setCompany("SCU");
        excelConfig.setSubject("提交记录表");
        excelConfig.setTitle("提交记录");
        excelConfig.setComments("备注信息暂无");

        response.setHeader("Access-Control-Expose-Headers", "filename");
        response.setHeader("filename", URLEncoder.encode("任务提交记录表.xls","utf-8"));
        return PoiUtils.exportExcel(all, excelConfig, TaskRecordExport.class);
    }
}