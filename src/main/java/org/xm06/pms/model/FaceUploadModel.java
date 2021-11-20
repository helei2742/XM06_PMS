package org.xm06.pms.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FaceUploadModel",description = "人脸上传参数的封装类")
public class FaceUploadModel {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("人脸图片base64数组的json字符串")
    private String facesJson;
    @ApiModelProperty("用户名")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFacesJson() {
        return facesJson;
    }

    public void setFacesJson(String facesJson) {
        this.facesJson = facesJson;
    }
}
