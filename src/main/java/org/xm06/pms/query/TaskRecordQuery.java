package org.xm06.pms.query;

import org.xm06.pms.base.BaseQuery;

public class TaskRecordQuery extends BaseQuery {
    private Integer userId;
    private Integer taskId;

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
}
