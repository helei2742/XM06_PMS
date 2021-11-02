package org.xm06.pms.query;

import org.xm06.pms.base.BaseQuery;

public class ProjectQuery extends BaseQuery {
    private Integer groupId;
    private Integer creatorId;

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
