package org.xm06.pms.query;

import org.xm06.pms.base.BaseQuery;

public class AnnounceQuery extends BaseQuery {
    public static final Integer QUERYALLANNOUNCE = 1;
    public static final Integer QUERYMYANNOUNCE = 2;

    public static final Integer ORDERBYDATEDESC = 1;
    public static final Integer ORDERYBYDATEASC= 2;

    private Integer userId;
    private Integer queryType;
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
