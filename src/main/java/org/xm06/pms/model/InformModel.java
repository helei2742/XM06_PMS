package org.xm06.pms.model;

import io.swagger.models.auth.In;

import java.util.List;

public class InformModel {
    @Override
    public String toString() {
        return "InformModel{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", sendUser=" + sendUser +
                ", sendDate=" + sendDate +
                ", message='" + message + '\'' +
                ", notRead=" + notRead +
                ", notReadList=" + notReadList +
                '}';
    }


    private Long id;
    private Integer groupId;
    private UserModel sendUser;
    private long sendDate;
    private String message;
    private Boolean notRead;

    private List<Long> notReadList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getNotRead() {
        return notRead;
    }

    public void setNotRead(Boolean notRead) {
        this.notRead = notRead;
    }

    public List<Long> getNotReadList() {
        return notReadList;
    }

    public void setNotReadList(List<Long> notReadList) {
        this.notReadList = notReadList;
    }
}
