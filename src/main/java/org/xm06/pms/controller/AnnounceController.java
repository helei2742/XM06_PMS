package org.xm06.pms.controller;

import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.query.AnnounceQuery;
import org.xm06.pms.service.AnnounceService;
import org.xm06.pms.vo.Announce;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/announce")
@Api(value = "AnnounceController", tags = "公告模块接口")
@ApiSupport(author = "914577981@qq.com")
public class AnnounceController extends BaseController {
    @Autowired
    AnnounceService announceService;

    @PostMapping("/createAnnounce")
    @ResponseBody
    @ApiOperation(value = "创建公告接口",notes = "需要传入创建人userId,公告标题title,公告内容mainBody")
    @ApiOperationSupport(includeParameters = {"announce.userId", "announce.title","announce.mainBody"})
    public ResultInfo createAnnounce(@RequestBody Announce announce){
        announceService.createAnnounce(announce);
        return success("发布公告成功", 200, null);
    }

    @PostMapping("/pageQueryAnnounce")
    @ResponseBody
    @ApiOperation(value = "分页查询公告接口",notes = "必须传入queryType(1代表查询全部，2代表查询用户创建的）" +
            "和orderType（1代表创建时间降序，2代表创建时间升序),queryType为1时userId必传.可选传分页参数当前页page和每页大小limit")
    @ApiOperationSupport(includeParameters = {"announceQuery.queryType", "announceQuery.orderType","announceQuery.limit","announceQuery.page"})
    public ResultInfo pageQueryAnnounce(@RequestBody AnnounceQuery announceQuery){
        PageInfo<Announce> all = announceService.pageQueryAnnounce(announceQuery);
        return success("", 200, all);
    }

    @PostMapping("/queryAnnounceById")
    @ResponseBody
    @ApiOperation(value = "根据announceId查询公告接口",notes = "需传入announceId")
    public ResultInfo queryAnnounceById(@RequestBody Map<String,Integer> map){
        Announce a = announceService.queryAnnounceById(map.get("announceId"));
        return success("",200,a);
    }

    @PostMapping("/alterAnnounce")
    @ResponseBody
    @ApiOperation(value = "修改公告信息接口",notes = "可修改字段有title,mainBody可宣传，必传announce的id")
    @ApiOperationSupport(includeParameters = {"announce.title", "announce.mainBody","announce.id"})
    public ResultInfo alterAnnounce(@RequestBody Announce announce){
        announceService.alterAnnounce(announce);
        return success("", 200, null);
    }

    @PostMapping("/deleteAnnounceById")
    @ResponseBody
    @ApiOperation(value = "删除公告接口",notes = "需传入announce的id, userId")
    @ApiOperationSupport(includeParameters = {"announce.userId","announce.id"})
    public ResultInfo deleteAnnounce(@RequestBody Announce announce){
        announceService.deleteAnnounce(announce);
        return success("", 200, null);
    }

    @PostMapping("/deleteSelectedAnnounce")
    @ResponseBody
    @ApiOperation(value = "批量删除公告接口",notes = "需删除的公告id放在announceIds数组中传入，还需传入userId")
    public ResultInfo deleteSelectedRecord(@RequestBody Map<String,Object> s){
        List<Integer> announceIds = (List<Integer>) s.get("announceIds");
        Integer userId = (Integer) s.get("userId");
        announceService.deleteSelectedAnnounce(announceIds, userId);
        return success("删除选中成功", 200, null);
    }

}
