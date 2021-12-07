package org.xm06.pms.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.xm06.pms.base.BaseQuery;

@ApiModel(value = "InformQuery",description = "通知消息查询条件封装类")
public class InformQuery extends BaseQuery {
    @ApiModelProperty(value = "用户Id", example = "1",required = true)
    private Integer userId;
    @ApiModelProperty(value = "小组id",example = "1", required = true)
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
