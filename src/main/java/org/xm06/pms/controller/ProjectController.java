package org.xm06.pms.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.query.ProjectQuery;
import org.xm06.pms.service.ProjectService;
import org.xm06.pms.vo.Project;

@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Autowired
    ProjectService projectService;


    /**
     * 创建小组，必须传入projectName, creatorId, groupId
     * @param project
     * @return
     */
    @RequestMapping(value = "/create", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo createProject(Project project){
        projectService.addProject(project);
        return success("创建项目成功", 200, null);
    }


    /**
     * 根据小组名称分页查询项目
     * @param query 需传入当前页数page，每页的数量limit，和groupId
     * @return
     */
    @RequestMapping(value = "/pageQueryGroupProject", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo queryByGroupId(ProjectQuery query){
        PageInfo<Project> pageInfo = projectService.pageQueryGroupProject(query);

        return success("查询小组项目成功", 200, pageInfo);
    }

    /**
     * 根据创建者分页查询项目
     * @param query 需传入当前页数page，每页的数量limit，和creatorId
     * @return
     */
    @RequestMapping(value = "/pageQueryProjectByCreatorId", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo pageQueryProjectByCreatorId(ProjectQuery query){
        PageInfo<Project> pageInfo = projectService.pageQueryProjectByUserId(query);
        return success("查询创建项目成功", 200, pageInfo);
    }

}
