package org.xm06.pms.model;

import io.swagger.models.auth.In;

import java.util.List;

public class InformModel {
    @Override
    public String toString() {
        return "InformModel{" +
                "groupId=" + groupId +
                "id=" + id +
                ", sendUser=" + sendUser +
                ", sendDate=" + sendDate +
                ", message='" + message + '\'' +
                '}';
    }

    private Long id;
    private Integer groupId;
    private UserModel sendUser;
    private long sendDate;
    private String message;
    private Boolean notRead;

    private List<Long> notReadList;
    private Boolean clear;

    public Boolean getClear() {
        return clear;
    }

    public void setClear(Boolean clear) {
        this.clear = clear;
    }

    public List<Long> getNotReadList() {
        return notReadList;
    }

    public void setNotReadList(List<Long> notReadList) {
        this.notReadList = notReadList;
    }

    public Boolean getNotRead() {
        return notRead;
    }

    public void setNotRead(Boolean notRead) {
        this.notRead = notRead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public UserModel getSendUser() {
        return sendUser;
    }

    public void setSendUser(UserModel sendUser) {
        this.sendUser = sendUser;
    }

    public long getSendDate() {
        return sendDate;
    }

    public void setSendDate(long sendDate) {
        this.sendDate = sendDate;
    }
}
