package org.xm06.pms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "TaskRecord",description = "任务提交记录的实体类")
public class TaskRecord {
    @ApiModelProperty("主键id")
    private Integer id;
    @ApiModelProperty("任务id")
    private Integer taskId;
    @ApiModelProperty("用户id，提交的用户")
    private Integer userId;
    @ApiModelProperty("提交的描述")
    private String description;
    @ApiModelProperty("提交文件在服务器的保存路径")
    private String fileUrl;
    @ApiModelProperty("提交的用户实体类")
    private User user;
    @ApiModelProperty("提交日期")
    private Date submitDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }
}