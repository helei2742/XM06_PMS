package org.xm06.pms.controller;

import com.github.pagehelper.PageInfo;
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
public class AnnounceController extends BaseController {
    @Autowired
    AnnounceService announceService;

    @PostMapping("/createAnnounce")
    @ResponseBody
    public ResultInfo createAnnounce(@RequestBody Announce announce){
        announceService.createAnnounce(announce);
        return success("发布公告成功", 200, null);
    }
    @PostMapping("/pageQueryAnnounce")
    @ResponseBody
    public ResultInfo pageQueryAnnounce(@RequestBody AnnounceQuery announceQuery){
        PageInfo<Announce> all = announceService.pageQueryAnnounce(announceQuery);
        return success("", 200, all);
    }
    @PostMapping("/queryAnnounceById")
    @ResponseBody
    public ResultInfo queryAnnounceById(@RequestBody Map<String,Integer> map){
        Announce a = announceService.queryAnnounceById(map.get("announceId"));
        return success("",200,a);
    }
    @PostMapping("/alterAnnounce")
    @ResponseBody
    public ResultInfo alterAnnounce(@RequestBody Announce announce){
        announceService.alterAnnounce(announce);
        return success("", 200, null);
    }
    @PostMapping("/deleteAnnounceById")
    @ResponseBody
    public ResultInfo deleteAnnounce(@RequestBody Announce announce){
        announceService.deleteAnnounce(announce);
        return success("", 200, null);
    }

    @PostMapping("/deleteSelectedAnnounce")
    @ResponseBody
    public ResultInfo deleteSelectedRecord(@RequestBody Map<String,Object> s){
        List<Integer> announceIds = (List<Integer>) s.get("announceIds");
        Integer userId = (Integer) s.get("userId");
        announceService.deleteSelectedAnnounce(announceIds, userId);
        return success("删除选中成功", 200, null);
    }

}
