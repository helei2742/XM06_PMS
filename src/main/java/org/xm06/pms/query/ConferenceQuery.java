package org.xm06.pms.query;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.xm06.pms.base.BaseQuery;

@ApiModel(value = "ConferenceQuery",description = "会议查询参数封装类")
public class ConferenceQuery extends BaseQuery {

    /*
     * 排序条件
     * 1、代表按照conference创建时间降序
     * 2、代表按照conference创建时间升序
     * 3、代表按照conference开始时间降序
     * 4、代表按照conference开始时间升序
     */
    public static final Integer CREATEDATEDESC = 1;
    public static final Integer CREATEDATEASC = 2;
    public static final Integer BEGINDEGREEDESC = 3;
    public static final Integer BEGINDEGREEASC = 4;

    /*
     * 查询条件
     */
    public static final Integer PAGEQUERYCONFERENCEBYUSERID = 1;
    public static final Integer PAGEQUERYCOMINGCONFERENCE = 2;
    public static final Integer PAGEQUERYFINISHCONFERENCE = 3;
    public static final Integer PAGEQUERYCONFERENCEBYGROUPID = 4;
    public static final Integer PAGEQUERYCONFERENCEBYNAME = 5;
    public static final Integer PAGEQUERYCONFERENCEBYNAMEANDCREATORID = 6;
    public static final Integer PAGEQUERYCONFERENCEBYNAMEANDGROUPID = 7;
    public static final Integer PAGEQUERYCONFERENCEBYALL = 8;
    public static final Integer PAGEQUERYCONFERENCEBYCREATORIDANDGROUPID = 9;


    @ApiModelProperty("会议名字")
    private String conferenceName;

    @ApiModelProperty("小组id")
    private Integer groupId;

    @ApiModelProperty("创建者id")
    private Integer creatorId;

    @ApiModelProperty(value = "查询类型",notes = "（2.查询小组的会议，3.查询还没开始的会议，" +
            "4，查询已经结束的会议，5.查询小组会议，1、会议名称模糊查询）同时需传入其他必须参数(对应2.userId, 1.conferenceName))")
    private Integer type;

    @ApiModelProperty(value = "排序类型",notes = "orderType(1、代表按照conference创建时间降序，2、代表按照conference创建时间升序" +
            "3、代表按照conference开始时间降序, 4、代表按照conference开始时间升序）")
    private Integer orderType = ConferenceQuery.BEGINDEGREEASC;

    /************************************************外部操作接口*****************************************************/

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }
}
