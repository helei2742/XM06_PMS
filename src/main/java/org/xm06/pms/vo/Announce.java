package org.xm06.pms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Announce",description = "公告实体类")
public class Announce {
    @ApiModelProperty("公告主键id")
    private Integer id;
    @ApiModelProperty("公告创建人id， 也可以在修改等操作中传入修改者的userId进行接收")
    private Integer userId;
    @ApiModelProperty("公告标题")
    private String title;
    @ApiModelProperty("公告内容")
    private String mainBody;
    @ApiModelProperty("公告创建时间")
    private Date createDate;
    @ApiModelProperty("公告更新时间")
    private Date updateDate;
    @ApiModelProperty("公告是否有效")
    private Boolean isValid;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMainBody() {
        return mainBody;
    }

    public void setMainBody(String mainBody) {
        this.mainBody = mainBody == null ? null : mainBody.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
}