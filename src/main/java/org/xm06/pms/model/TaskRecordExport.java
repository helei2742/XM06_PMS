package org.xm06.pms.model;

import java.util.Date;

public class TaskRecordExport {
    private String taskName;
    private String submitUserName;
    private String trueName;
    private String submitDesc;
    private Date submitDate;
    private String submitFileName;

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getSubmitDesc() {
        return submitDesc;
    }

    public void setSubmitDesc(String submitDesc) {
        this.submitDesc = submitDesc;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getSubmitUserName() {
        return submitUserName;
    }

    public void setSubmitUserName(String submitUserName) {
        this.submitUserName = submitUserName;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getSubmitFileName() {
        return submitFileName;
    }

    public void setSubmitFileName(String submitFileName) {
        this.submitFileName = submitFileName;
    }
}
