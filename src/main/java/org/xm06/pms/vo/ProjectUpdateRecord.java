package org.xm06.pms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "ProjectUpdateRecord",description = "项目进度实体类")
public class ProjectUpdateRecord {
    @ApiModelProperty("主键id")
    private Integer id;
    @ApiModelProperty(value = "用户id",notes = "主要指提交的用户")
    private Integer userId;
    @ApiModelProperty(value = "用户",notes = "主要指提交的用户")
    private User user;
    @ApiModelProperty("项目id")
    private Integer projectId;
    @ApiModelProperty("提交描述")
    private String submitDesc;
    @ApiModelProperty("提交进度")
    private Float submitDegree;
    @ApiModelProperty("上传文件在服务器的保存路径")
    private String uploadFileSrc;
    @ApiModelProperty("进度提交时间")
    private Date createDate;
    @ApiModelProperty("提交记录的更新时间")
    private Date updateDate;

    private Boolean isValid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getSubmitDesc() {
        return submitDesc;
    }

    public void setSubmitDesc(String submitDesc) {
        this.submitDesc = submitDesc == null ? null : submitDesc.trim();
    }

    public Float getSubmitDegree() {
        return submitDegree;
    }

    public void setSubmitDegree(Float submitDegree) {
        this.submitDegree = submitDegree;
    }

    public String getUploadFileSrc() {
        return uploadFileSrc;
    }

    public void setUploadFileSrc(String uploadFileSrc) {
        this.uploadFileSrc = uploadFileSrc == null ? null : uploadFileSrc.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
}