package org.xm06.pms.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.service.SystemRecordService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Controller
@Api(value = "InitController",tags = "系统相关接口")
@ApiSupport(author = "914577981@qq.com")
public class InitController extends BaseController {
    @RequestMapping(value = "/",method={RequestMethod.GET,RequestMethod.POST})
    @ApiOperation("跳转到默认主页")
    public String index(HttpServletResponse response) throws IOException {
        response.sendRedirect("index.html");
        return null;
    }
    @RequestMapping(value = "/error",method={RequestMethod.GET,RequestMethod.POST})
    @ApiOperation("响应错误页面，可在前端收到响应代码为500等调用该接口")
    public String error(HttpServletResponse response) throws IOException {
        return "error";
    }

    @Autowired
    SystemRecordService systemRecordService;

    @RequestMapping(value = "/systemRecord/7day",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("系统7天的各种记录数据接口")
    public Map<String, Object> systemRecord7day() {
        return systemRecordService.last7DaySystemRecord();
    }

    @RequestMapping(value = "/systemRecord/total",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("系统的总记录数据接口")
    public Map<String, Object> systemRecordTotal() {
        return systemRecordService.systemTotalRecord();
    }
}
