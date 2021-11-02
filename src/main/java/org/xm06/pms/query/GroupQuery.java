package org.xm06.pms.query;

import org.xm06.pms.base.BaseQuery;

public class GroupQuery extends BaseQuery {
    private Integer managerId; //管理员id
    private String groupName;// 小组名字
    private Integer userId; // 根据用户id查询小组

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
