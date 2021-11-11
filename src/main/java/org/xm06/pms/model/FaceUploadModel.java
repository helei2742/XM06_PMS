package org.xm06.pms.model;

public class FaceUploadModel {
    private Integer userId;
    private String facesJson;

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
