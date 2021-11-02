package org.xm06.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.service.MemoServe;
import org.xm06.pms.vo.Memo;

import java.util.List;

@Controller
@RequestMapping("/memo")
public class MemoController extends BaseController {
    @Autowired
    private MemoServe memoServe;

    @RequestMapping(value = "/add",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo add(Integer userId, String memo,String createDate, String staleDate) {
        memoServe.addMemo(userId, memo, createDate, staleDate);
        return success("保存便签成功", 200, null);
    }

    @RequestMapping(value = "/userMemo",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo queryUserMemo(Integer userId){
        List<Memo> memos = memoServe.queryUserMemo(userId);
        return success("查询成功", 200, memos);
    }

    @RequestMapping(value = "/removeMemo",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo removeMemo(Integer userId,Integer memoId){
        memoServe.removeMemo(userId, memoId);
        return success("删除便签成功", 200, null);
    }

    @RequestMapping(value = "/confirmMemo",produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo confirmMemo(Integer userId, Integer memoId){
        memoServe.confirmMemo(userId, memoId);
        return success("确认便签成功", 200, null);
    }
}
