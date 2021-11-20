package org.xm06.pms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.service.MemoServe;
import org.xm06.pms.vo.Memo;

import java.util.List;

@Controller
@RequestMapping("/memo")
@Api(value="MemoController", tags ="便签模块接口")
public class MemoController extends BaseController {
    @Autowired
    private MemoServe memoServe;

    @RequestMapping(value = "/add",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("添加便签接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="userId", value = "用户的id", required = true, dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name ="memo", value = "便签内容", required = true, dataType = "String",dataTypeClass = String.class),
            @ApiImplicitParam(name ="createDate", value = "创建时间，yyyy年MM月dd日HH时mm分ss秒格式", required = true, dataType = "String",dataTypeClass = String.class),
            @ApiImplicitParam(name ="staDateDate", value = "便签过期时间时间，yyyy年MM月dd日HH时mm分ss秒格式", required = true, dataType = "String",dataTypeClass = String.class),
    })
    public ResultInfo add(Integer userId, String memo,String createDate, String staleDate) {
        memoServe.addMemo(userId, memo, createDate, staleDate);
        return success("保存便签成功", 200, null);
    }


    @RequestMapping(value = "/userMemo",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("查询便签接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="userId", value = "用户的id", required = true, dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo queryUserMemo(Integer userId){
        List<Memo> memos = memoServe.queryUserMemo(userId);
        return success("查询成功", 200, memos);
    }



    @RequestMapping(value = "/removeMemo",produces = "application/json;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiOperation("移除便签接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="userId", value = "用户的id", required = true, dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name ="memoId", value = "便签的id", required = true, dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo removeMemo(Integer userId,Integer memoId){
        memoServe.removeMemo(userId, memoId);
        return success("删除便签成功", 200, null);
    }



    @RequestMapping(value = "/confirmMemo",produces = "application/json;charset=utf-8",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @ApiOperation("确认便签完成便签接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="userId", value = "用户的id", required = true, dataType = "Integer",dataTypeClass = Integer.class),
            @ApiImplicitParam(name ="memoId", value = "便签的id", required = true, dataType = "Integer",dataTypeClass = Integer.class)
    })
    public ResultInfo confirmMemo(Integer userId, Integer memoId){
        memoServe.confirmMemo(userId, memoId);
        return success("确认便签成功", 200, null);
    }
}
