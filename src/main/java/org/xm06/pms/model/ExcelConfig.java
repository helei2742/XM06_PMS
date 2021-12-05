package org.xm06.pms.model;

public class ExcelConfig {
    /**
     * 文档类别
     */
    private String category="test";
    /**
     * 文档管理员
     */
    private String manager="test";
    /**
     * 组织机构
     */
    private String company="test";
    /**
     * 设置文档主题
     */
    private String subject="test";
    /**
     * 设置文档标题
     */
    private String title="test";
    /**
     * 设置文档作者
     */
    private String author="test";
    /**
     * 文档备注
     */
    private String comments="test";
    /**
     * sheet名字
     */
    private String sheetName="test";

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
