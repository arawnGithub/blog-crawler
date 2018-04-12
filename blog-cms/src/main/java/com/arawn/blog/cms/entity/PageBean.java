package com.arawn.blog.cms.entity;

/**
 * 分页实体
 */
public class PageBean {

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页显示记录数
     */
    private int pageSize;

    /**
     * 起始索引
     */
    private int start;

    public PageBean(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        start = (page - 1) * pageSize;
        return start;
    }

}
