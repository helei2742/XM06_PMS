package org.xm06.pms.query;

import org.xm06.pms.base.BaseQuery;

public class InformQuery extends BaseQuery {
    private Integer userId;
    private Integer groupId;

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
}
