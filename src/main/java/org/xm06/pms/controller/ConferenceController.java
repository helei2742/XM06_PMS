package org.xm06.pms.controller;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xm06.pms.base.ResultInfo;
import org.xm06.pms.base.BaseController;
import org.xm06.pms.query.ConferenceQuery;
import org.xm06.pms.service.ConferenceService;
import org.xm06.pms.vo.Conference;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = "/conference")
@Api(value = "ConferenceController", tags = "会议模块接口")
public class ConferenceController extends BaseController{

    @Autowired
    private ConferenceService conferenceService;

    @PostMapping(value = "/pageQueryAllConference", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo conference_index(@RequestBody @Valid ConferenceQuery conferenceQuery) {
        List<Conference> conferences = conferenceService.pageQueryAllConference(conferenceQuery);
        return success("查询会议成功", 200, conferences);
    }

    @PostMapping(value = "/pageQueryBeforeConference", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo before_conference(@RequestBody @Valid ConferenceQuery conferenceQuery) {
        List<Conference> conferences = conferenceService.pageQueryAllConference(conferenceQuery);
        // System.out.println(conferences.getList().get(0));
        return success("查询会议成功", 200, conferences);
    }

    @PostMapping(value = "/pageQueryAfterConference", produces = "application/json;charset=utf-8")
    @ResponseBody
    public ResultInfo after_conference(@RequestBody @Valid ConferenceQuery conferenceQuery) {
        List<Conference> conferences = conferenceService.pageQueryAllConference(conferenceQuery);
        return success("查询会议成功", 200, conferences);
    }

    @PostMapping(value = "/add", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiOperation(value = "发布会议接口",notes = "应传入：conferenceName，conferenceInfo, conferenceDate，hourLong, groupId，" +
            "address, creatorId, createDate, groupId")
    public ResultInfo addConference(@RequestBody @Valid Conference conference) {
        // System.out.println(conference);
        conferenceService.addConference(conference);
        return success("发布会议成功", 200, null);
    }

    @PostMapping(value = "/delete", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiModelProperty(value = "删除会议记录")
    public ResultInfo deleteConference(@RequestBody @Valid Conference conference) {
        conferenceService.deleteConference(conference.getId(), conference.getUserId());
        return success("删除会议成功", 200, null);
    }

    @PostMapping(value = "/modify", produces = "application/json;charset=utf-8")
    @ResponseBody
    @ApiModelProperty(value = "修改会议记录")
    public ResultInfo modifyConference(@RequestBody @Valid Conference conference) {
        conferenceService.modifyConference(conference, conference.getUserId());
        return success("修改会议信息成功", 200, null);
    }
}
