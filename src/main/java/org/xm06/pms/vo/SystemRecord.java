package org.xm06.pms.vo;

import java.util.Date;

public class SystemRecord {
    private Integer id;

    private Integer loginCount;

    private Integer registerCount;

    private Integer taskSubmitCount;

    private Integer taskCreateCount;

    private Integer groupCreateCount;

    private Integer projectCreateCount;

    private Integer projectUpdateCount;

    private Date recordDate;

    public static final String[] titles = {"登录人数", "注册人数", "提交任务数量", "创建任务数量", "创建小组数量",
            "创建项目数量", "提交项目进度数量"};
    @Override
    public String toString() {
        return "SystemRecord{" +
                "id=" + id +
                ", loginCount=" + loginCount +
                ", registerCount=" + registerCount +
                ", taskSubmitCount=" + taskSubmitCount +
                ", taskCreateCount=" + taskCreateCount +
                ", groupCreateCount=" + groupCreateCount +
                ", projectCreateCount=" + projectCreateCount +
                ", projectUpdateCount=" + projectUpdateCount +
                ", recordDate=" + recordDate +
                '}';
    }

    public Integer get(String s) {
        if("登录人数".equals(s)){
            return loginCount;
        }
        if("注册人数".equals(s)){
            return registerCount;
        }
        if("提交任务数量".equals(s)){
            return taskSubmitCount;
        }
        if("创建任务数量".equals(s)){
            return taskCreateCount;
        }
        if("创建小组数量".equals(s)){
            return groupCreateCount;
        }
        if("创建项目数量".equals(s)){
            return  projectCreateCount;
        }
        if("提交项目进度数量".equals(s)){
            return projectUpdateCount;
        }
        return 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }

    public Integer getTaskSubmitCount() {
        return taskSubmitCount;
    }

    public void setTaskSubmitCount(Integer taskSubmitCount) {
        this.taskSubmitCount = taskSubmitCount;
    }

    public Integer getTaskCreateCount() {
        return taskCreateCount;
    }

    public void setTaskCreateCount(Integer taskCreateCount) {
        this.taskCreateCount = taskCreateCount;
    }

    public Integer getGroupCreateCount() {
        return groupCreateCount;
    }

    public void setGroupCreateCount(Integer groupCreateCount) {
        this.groupCreateCount = groupCreateCount;
    }

    public Integer getProjectCreateCount() {
        return projectCreateCount;
    }

    public void setProjectCreateCount(Integer projectCreateCount) {
        this.projectCreateCount = projectCreateCount;
    }

    public Integer getProjectUpdateCount() {
        return projectUpdateCount;
    }

    public void setProjectUpdateCount(Integer projectUpdateCount) {
        this.projectUpdateCount = projectUpdateCount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}