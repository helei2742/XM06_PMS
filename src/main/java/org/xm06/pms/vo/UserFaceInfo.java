package org.xm06.pms.vo;

import java.util.Date;

public class UserFaceInfo {
    private Integer id;

    private Integer userId;

    private String faceYmlSrc;

    private String faceImgFolderSrc;

    private Date createDate;

    private Date updateDate;

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

    public String getFaceYmlSrc() {
        return faceYmlSrc;
    }

    public void setFaceYmlSrc(String faceYmlSrc) {
        this.faceYmlSrc = faceYmlSrc == null ? null : faceYmlSrc.trim();
    }

    public String getFaceImgFolderSrc() {
        return faceImgFolderSrc;
    }

    public void setFaceImgFolderSrc(String faceImgFolderSrc) {
        this.faceImgFolderSrc = faceImgFolderSrc;
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