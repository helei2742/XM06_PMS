package org.xm06.pms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Inform",description = "通知消息的实体类")
public class Inform {
    @Override
    public String toString() {
        return "Inform{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", sendUserId=" + sendUserId +
                ", sendDate=" + sendDate +
                ", message='" + message + '\'' +
                '}';
    }

    @ApiModelProperty("主键id")
    private Long id;
    @ApiModelProperty("该消息的小组")
    private Integer groupId;
    @ApiModelProperty("发送者id")
    private Integer sendUserId;
    @ApiModelProperty("发送日期")
    private Date sendDate;
    @ApiModelProperty("消息内容")
    private String message;

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

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}