package org.xm06.pms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.assertj.core.internal.ObjectArrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.service.SystemRecordService;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
public class InitController extends BaseController {
    @RequestMapping("/")
    @ResponseBody
    public String index(HttpServletResponse response) throws IOException {
        response.sendRedirect("index.html");
        return null;
    }

    @Autowired
    SystemRecordService systemRecordService;

    @RequestMapping("/systemRecord/7day")
    @ResponseBody
    public Map<String, Object> systemRecord7day() {
        return systemRecordService.last7DaySystemRecord();
    }
    @RequestMapping("/systemRecord/total")
    @ResponseBody
    public Map<String, Object> systemRecordTotal() {
        return systemRecordService.systemTotalRecord();
    }
}
