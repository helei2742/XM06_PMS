package org.xm06.pms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ApiModel(value = "Project",description = "项目的实体类")
public class Project {
    public Integer[] getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Integer[] groupIds) {
        this.groupIds = groupIds;
    }

    @ApiModelProperty("负责该项目的小组id列表")
    private Integer[] groupIds;
    @ApiModelProperty("项目id")
    private Integer id;
    @ApiModelProperty("项目名称")
    private String projectName;
    @ApiModelProperty("项目描述")
    private String projectDesc;
    @ApiModelProperty("创建者id")
    private Integer creatorId;
    private Integer groupId;
    @ApiModelProperty("完成度")
    private Double completionDegree;
    @ApiModelProperty("创建日期")
    private Date createDate;
    @ApiModelProperty("更新日期")
    private Date updateDate;
    @ApiModelProperty("是否为公开项目")
    private Boolean isPublic;
    private Boolean isValid;

    @ApiModelProperty("项目负责小组实体的列表")
    private List<Group> groupList;


    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc == null ? null : projectDesc.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Double getCompletionDegree() {
        return completionDegree;
    }

    public void setCompletionDegree(Double completionDegree) {
        this.completionDegree = completionDegree;
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

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }
}