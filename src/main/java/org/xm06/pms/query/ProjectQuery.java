package org.xm06.pms.query;

import org.xm06.pms.base.BaseQuery;

public class ProjectQuery extends BaseQuery {
    /*
     * 排序条件
     * 1、代表按照project创建时间降序
     * 2、代表按照project创建时间升序
     * 3、代表按照project完成的降序
     * 4、代表按照project完成度升序
     */
    public static final Integer CREATEDATEDESC = 1;
    public static final Integer CREATEDATEASC = 2;
    public static final Integer COMPLETIONDEGREEDESC = 3;
    public static final Integer COMPLETIONDEGREEASC = 4;

    /*
     * 查询条件
     */
    public static final Integer PAGEQUERYPROJECTBYUSERID = 1;
    public static final Integer PAGEQUERYUSERJOINEDGROUPPROJECT = 2;
    public static final Integer PAGEQUERYPUBLICPROJECT = 3;
    public static final Integer PAGEQUERYNAMELIKEPROJECT = 4;



    private Integer groupId;
    private Integer userId;
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 查询的条件
     */
    private Integer type;


    private Integer orderType = ProjectQuery.CREATEDATEDESC;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
