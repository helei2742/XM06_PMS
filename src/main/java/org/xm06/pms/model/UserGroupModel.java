package org.xm06.pms.model;

public class UserGroupModel {
    private String groupName;
    private Integer groupId;
    private String userName;
    private Integer userId;
    public UserGroupModel() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserGroupModel(String groupName, Integer groupId, String userName, Integer userId) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.userName = userName;
        this.userId = userId;
    }


}
