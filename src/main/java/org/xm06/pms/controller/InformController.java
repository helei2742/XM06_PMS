package org.xm06.pms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.model.InformModel;
import org.xm06.pms.query.InformQuery;
import org.xm06.pms.service.InformService;
import org.xm06.pms.websocket.WebSocketServer;

import java.util.List;
import java.util.Vector;


@Controller
@RequestMapping("inform")
public class InformController extends BaseController {

    @Autowired
    InformService informService;

    @PostMapping("/pageQueryInformRecord")
    @ResponseBody
    public ResultInfo pageQueryInformRecord(@RequestBody InformQuery informQuery){
        PageInfo<InformModel> pi = informService.pageQueryInformRecord(informQuery);
        return success("查询消息记录成功", 200, pi);
    }

    @PostMapping("/queryInformIn3day")
    @ResponseBody
    public ResultInfo queryInformIn3day(@RequestBody InformQuery informQuery){
        Vector<InformModel> informModels = WebSocketServer.groupMessage.get(informQuery.getGroupId());
        return success("查询消息记录成功", 200, informModels);
    }

    @PostMapping(value = "/clearNotRead")
    @ResponseBody
    public ResultInfo clearNotRead(@RequestBody String s){
        System.out.println(s);
        JSONObject jsonObject = JSON.parseObject(s);
        Integer userId = jsonObject.getInteger("userId");
        Integer groupId = jsonObject.getInteger("groupId");
        List<Long> notReadList = JSON.parseArray(jsonObject.getString("notReadList"), Long.class);
        informService.clearNotRead(userId, groupId, notReadList);
        return success("",200, notReadList);
    }
}
