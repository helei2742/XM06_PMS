package org.xm06.pms.controller;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.query.ConferenceQuery;
import org.xm06.pms.service.ConferenceService;
import org.xm06.pms.vo.Conference;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/conference")
@Api(value = "ConferenceController", tags = "会议模块接口")
public class ConferenceController extends BaseController{

    @Autowired
    private ConferenceService conferenceService;

    @PostMapping(value = "/pageQueryAllConference", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo conference_index(@RequestBody @Valid ConferenceQuery conferenceQuery) {
        PageInfo<Conference> conferences = conferenceService.pageQueryAllConference(conferenceQuery);
        return success("查询会议成功", 200, conferences);
    }

    @PostMapping(value = "/add", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "发布会议接口",notes = "应传入：conferenceName，conferenceInfo, conferenceDate，hourLong, groupId，" +
            "address, creatorId, createDate, groupId")
    public ResultInfo addConference(@RequestBody @Valid Conference conference) {
        conferenceService.addConference(conference);
        return success("发布会议成功", 200, null);
    }

//    public String ConferenceIndex(){
//        return "conference_index.html";
//    }
}
