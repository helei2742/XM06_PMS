package org.xm06.pms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.xm06.pms.base.BaseQuery;

@ApiModel(value = "AnnounceQuery", description = "公告查询参数封装类")
public class AnnounceQuery extends BaseQuery {
    public static final Integer QUERYALLANNOUNCE = 1;
    public static final Integer QUERYMYANNOUNCE = 2;

    public static final Integer ORDERBYDATEDESC = 1;
    public static final Integer ORDERYBYDATEASC= 2;

    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("查询类型， 1代表查询全部， 2代表查询用户创建的(此时userId必传)")
    private Integer queryType;
    @ApiModelProperty("排序类型， 1代表时间降序，2代表时间升序")
    private Integer orderType;

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
