package org.xm06.pms.vo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Project {
    public Integer[] getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Integer[] groupIds) {
        this.groupIds = groupIds;
    }

    private Integer[] groupIds;

    @Override
    public String toString() {
        return "Project{" +
                "groupIds=" + Arrays.toString(groupIds) +
                ", id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                ", creatorId=" + creatorId +
                ", groupId=" + groupId +
                ", completionDegree=" + completionDegree +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isPublic=" + isPublic +
                ", isValid=" + isValid +
                ", groupList=" + groupList +
                '}';
    }

    private Integer id;

    private String projectName;

    private String projectDesc;

    private Integer creatorId;

    private Integer groupId;

    private Double completionDegree;

    private Date createDate;

    private Date updateDate;

    private Boolean isPublic;
    private Boolean isValid;

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