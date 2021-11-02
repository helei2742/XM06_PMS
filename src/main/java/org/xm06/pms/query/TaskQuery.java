package org.xm06.pms.query;

import org.xm06.pms.base.BaseQuery;

public class TaskQuery extends BaseQuery {

    private Integer userId;
    private Integer groupId;
    private Integer creatorId;
    private Integer type;

    @Override
    public String toString() {
        return "TaskQuery{" +
                "userId=" + userId +
                ", groupId=" + groupId +
                ", creatorId=" + creatorId +
                ", type=" + type +
                '}';
    }

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
