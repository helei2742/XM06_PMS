package org.xm06.pms.vo;

import java.util.Date;

public class Conference {
    private Integer id;

    private String conferenceName;

    private String conferenceInfo;

    private Date conferenceDate;

    private String hourLong;

    private String address;

    private Integer creatorId;

    private Date createDate;

    private Integer groupId;

    private Integer userId;

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
        this.conferenceName = conferenceName == null ? null : conferenceName.trim();
    }

    public String getConferenceInfo() {
        return conferenceInfo;
    }

    public void setConferenceInfo(String conferenceInfo) {
        this.conferenceInfo = conferenceInfo == null ? null : conferenceInfo.trim();
    }

    public Date getConferenceDate() {
        return conferenceDate;
    }

    public void setConferenceDate(Date conferenceDate) {
        this.conferenceDate = conferenceDate;
    }

    public String getHourLong() {
        return hourLong;
    }

    public void setHourLong(String hourLong) {
        this.hourLong = hourLong == null ? null : hourLong.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}