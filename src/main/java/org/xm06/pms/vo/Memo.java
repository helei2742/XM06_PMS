package org.xm06.pms.vo;

import java.util.Date;

public class Memo {
    private Integer id;

    private Integer userId;

    private String memo;

    private Date staleDate;

    private Date createDate;

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