package org.xm06.pms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.xm06.pms.base.BaseQuery;

@ApiModel(value = "ProjectQuery",description = "项目查询参数的封装类")
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


    @ApiModelProperty("小组id")
    private Integer groupId;
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("项目名称")
    private String projectName;
    /**
     * 查询的条件
     */
    @ApiModelProperty(value = "查询条件类型",notes = "1代表查找用户创建的项目，2代表查找用户加入小组的项目，3代表查找所有公开的项目" +
            "4项目名称模糊查询）同时需传入其他必须参数(对应1.userId,2.userId,3.无需其他参数,4.projectName).")
    private Integer type;
    @ApiModelProperty(value = "排序类型",notes = "orderType(1、代表按照project创建时间降序，2、代表按照project创建时间升序，3、代表按照project完成的降序" +
            "4、代表按照project完成度升序）")
    private Integer orderType = ProjectQuery.CREATEDATEDESC;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
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
