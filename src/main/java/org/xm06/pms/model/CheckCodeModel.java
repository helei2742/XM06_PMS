package org.xm06.pms.model;


public class CheckCodeModel {
    private Integer userId;
    private Integer projectId;

    private String code;

    @Override
    public String toString() {
        return "CheckCodeModel{" +
                "userId=" + userId +
                ", projectId=" + projectId +
                ", code='" + code + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    private Long createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
