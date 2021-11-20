package org.xm06.pms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.xm06.pms.base.BaseQuery;

@ApiModel(value = "TaskQuery",description = "任务查询参数封装类")
public class TaskQuery extends BaseQuery {

    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("小组id")
    private Integer groupId;
    @ApiModelProperty("创建者id")
    private Integer creatorId;
    @ApiModelProperty(value = "查询类型",notes = "（2.查询用户创建的任务，3.查询还没过期的任务，" +
            "4，查询用户已经提交过的任务，5.查询过期的任务、6。查看小组的任务，7、查看未提交的任务, 1、或其他，查看所有任务)")
    private Integer type;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}
