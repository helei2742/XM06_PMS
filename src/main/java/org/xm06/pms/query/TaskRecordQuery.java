package org.xm06.pms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.xm06.pms.base.BaseQuery;

@ApiModel(value = "TaskRecordQuery",description = "任务记录查询参数封装类")
public class TaskRecordQuery extends BaseQuery {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("任务id")
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
