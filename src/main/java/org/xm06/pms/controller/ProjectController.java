package org.xm06.pms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
//import org.opencv.face.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.query.ProjectQuery;
import org.xm06.pms.query.ProjectRecordQuery;
import org.xm06.pms.service.ProjectService;
import org.xm06.pms.service.ProjectUploadRecordService;
import org.xm06.pms.vo.Project;
import org.xm06.pms.vo.ProjectUpdateRecord;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
@Api(value = "ProjectController", tags = "项目模块接口")
public class ProjectController extends BaseController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectUploadRecordService projectUploadRecordService;

    /**
     * 创建项目，必须传入projectName, creatorId, groupId
     * @param
     * @return
     */
    @PostMapping(value = "/create")
    @ResponseBody
    @ApiOperation(value = "创建项目接口",notes = "需传入projectName，creatorId,projectDesc,groups,isPublic")
    public ResultInfo createProject(@RequestBody @Valid Project project){
        projectService.addProject(project);
        return success("创建项目成功", 200, null);
    }


    @PostMapping("/queryByProjectName")
    @ResponseBody
    @ApiOperation(value = "根据项目名称查询项目接口",notes = "需传入projectName")
    public ResultInfo queryByProjectName(@RequestBody @Valid ProjectQuery projectQuery){
        Project p = projectService.queryByProjectName(projectQuery.getProjectName());
        return success("查询成功", 200, p);
    }

    @PostMapping("/queryById")
    @ResponseBody
    @ApiOperation(value = "根据项目Id查询项目接口",notes = "需传入projectId")
    public ResultInfo queryByProjectId(@RequestBody Map<String, String> map) {
        Integer projectId = Integer.valueOf(map.get("projectId"));
        Project project = projectService.queryByProjectId(projectId);
        return success("查询成功", 200, project);
    }

    /**
     * 根据type 的不同分页查询
     * @param projectQuery
     * @return
     */
    @PostMapping("/queryProjectList")
    @ResponseBody
    @ApiOperation(value = "根据不同的条件分页查询接口",notes = "本接口能够完成许多查询方式，查询时，排序方式和" +
            "查询方式是一定必须的，当查询方式type（： 1代表查找用户创建的项目，2代表查找用户加入小组的项目，3 代表查找所有公开的项目" +
            "4 项目名称模糊查询） 同时需传入其他必须参数(对应1.userId,2.userId,3.无需其他参数,4.projectName)." +
            "同时，分页查询的page(当前页,默认1) 和 limit(每页的数目，默认10) 也可传入。" +
            "排序方式orderType(1、代表按照project创建时间降序，2、代表按照project创建时间升序，3、代表按照project完成的降序" +
            "4、代表按照project完成度升序）")
    public ResultInfo queryProjectList(@RequestBody @Valid ProjectQuery projectQuery) {
        System.out.println(projectQuery);
        PageInfo<Project> pageInfo = projectService.queryProjectList(projectQuery);
        return success("查询成功", 200, pageInfo);
    }

    /**
     * 根据小组名称分页查询项目
     * @param query 需传入当前页数page，每页的数量limit，和groupId
     * @return
     */
    @PostMapping(value = "/pageQueryGroupProject", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "根据小组名称分页查询项目接口",notes = "需传入groupId,可选传入page,limit")
    public ResultInfo queryByGroupId(@RequestBody @Valid ProjectQuery query){
        PageInfo<Project> pageInfo = projectService.pageQueryGroupProject(query);
        return success("查询小组项目成功", 200, pageInfo);
    }

    /**
     * 根据创建者分页查询项目
     * @param query 需传入当前页数page，每页的数量limit，和creatorId
     * @return
     */
    @PostMapping(value = "/pageQueryProjectByCreatorId", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "根据创建者的id分页查找项目接口",notes ="需传入userId，可选传入page,limit")
    public ResultInfo pageQueryProjectByCreatorId(@RequestBody @Valid ProjectQuery query){
        PageInfo<Project> pageInfo = projectService.pageQueryProjectByUserId(query);
        return success("查询创建项目成功", 200, pageInfo);
    }

//--------------------------------------  项目进度 ——----------------
    /**
     * 提交项目进度
     * @return
     */
    @PostMapping(value = "/submitProjectDegree", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "提交项目进度接口",notes = "需传入userId,projectId,submitDegree,可选传入submitDesc,与file(需为FormData)")
    public ResultInfo submitProjectDegree(@RequestParam Map<String, String> map,@RequestParam(required = false) MultipartFile file)
            throws IOException {
        ProjectUpdateRecord record = new ProjectUpdateRecord();
        record.setUserId(Integer.parseInt(map.get("userId")));
        record.setProjectId(Integer.parseInt(map.get("projectId")));
        record.setSubmitDesc(map.get("submitDesc"));
        record.setSubmitDegree(Float.valueOf(map.get("submitDegree")));
        projectUploadRecordService.addProjectUpdateRecord(record, file);
        return success("添加成功", 200, null);
    }


    @PostMapping(value = "/pageQueryRecordList", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "分页查询项目记录接口",notes = "需传入projectId,可选传入page,limit")
    public ResultInfo pageQueryUpdateRecord(@RequestBody @Valid ProjectRecordQuery recordQuery)
            throws IOException {

        PageInfo<ProjectUpdateRecord> pageInfo =
                projectUploadRecordService.pageQueryProjectUpdateRecord(recordQuery);
        return success("添加成功", 200, pageInfo);
    }

}
