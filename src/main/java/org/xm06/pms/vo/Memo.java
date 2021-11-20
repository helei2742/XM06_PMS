package org.xm06.pms.vo;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Memo",description = "便签实体类")
public class Memo {
    @ApiModelProperty("主键id")
    private Integer id;
    @ApiModelProperty("该便签所属的用户id")
    private Integer userId;
    @ApiModelProperty("便签内容")
    private String memo;
    @ApiModelProperty("截至日期")
    private Date staleDate;
    @ApiModelProperty("创建日期")
    private Date createDate;
    @ApiModelProperty("是否完成")
    private Boolean isFinish;

    public Boolean getFinish() {
        return isFinish;
    }

    public void setFinish(Boolean finish) {
        isFinish = finish;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getStaleDate() {
        return staleDate;
    }

    public void setStaleDate(Date staleDate) {
        this.staleDate = staleDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}