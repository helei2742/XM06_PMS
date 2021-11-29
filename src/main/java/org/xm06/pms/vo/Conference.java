package org.xm06.pms.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Conference", description = "会议实体对象")
public class Conference {

    @ApiModelProperty("小组ID")
    private Integer groupId;

    @ApiModelProperty("会议ID")
    private Integer id;

    @ApiModelProperty("会议名字")
    private String conferenceName;

    @ApiModelProperty("会议信息")
    private String conferenceInfo;

    @ApiModelProperty("会议日期")
    private Date conferenceDate;

    @ApiModelProperty("会议时长")
    private String hour_long;

    @ApiModelProperty("会议地点")
    private String address;

    @ApiModelProperty("创建人ID")
    private Integer creatorId;
    @ApiModelProperty("创建者实体类")
    private User creator;

    @ApiModelProperty("创建日期")
    private Date createDate;

    /************************************************外部操作接口*****************************************/

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getConferenceInfo() {
        return conferenceInfo;
    }

    public void setConferenceInfo(String conferenceInfo) {
        this.conferenceInfo = conferenceInfo;
    }

    public Date getConferenceDate() {
        return conferenceDate;
    }

    public void setConferenceDate(Date conferenceDate) {
        this.conferenceDate = conferenceDate;
    }

    public String getHour_long() {
        return hour_long;
    }

    public void setHour_long(String hour_long) {
        this.hour_long = hour_long;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
