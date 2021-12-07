package org.xm06.pms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "AnnounceController",tags = "通知模块接口（一部分交给websocket处理了）")
@ApiSupport(author = "914577981@qq.com")
public class InformController extends BaseController {

    @Autowired
    InformService informService;

    @PostMapping("/pageQueryInformRecord")
    @ResponseBody
    @ApiOperation(value = "查询消息记录接口(数据库中的)",notes = "需传入userId,groupId")
    @ApiOperationSupport(includeParameters = {"informQuery.userId","informQuery.groupId"})
    public ResultInfo pageQueryInformRecord(@RequestBody InformQuery informQuery){
        PageInfo<InformModel> pi = informService.pageQueryInformRecord(informQuery);
        return success("查询消息记录成功", 200, pi);
    }

    @PostMapping("/queryInformIn3day")
    @ResponseBody
    @ApiOperation(value = "查询消息记录接口（3天持久化保存到数据库一次，这里查询内存中的)",notes = "需传入groupId")
    @ApiOperationSupport(includeParameters = {"informQuery.groupId"})
    public ResultInfo queryInformIn3day(@RequestBody InformQuery informQuery){
        Vector<InformModel> informModels = WebSocketServer.groupMessage.get(informQuery.getGroupId());
        return success("查询消息记录成功", 200, informModels);
    }

    @PostMapping(value = "/clearNotRead")
    @ResponseBody
    @ApiOperation(value = "清除未读消息接口（包括数据库与内存中的全部)",notes = "需传入userId,groupId,notReadList[](数组内容为消息id)")
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
