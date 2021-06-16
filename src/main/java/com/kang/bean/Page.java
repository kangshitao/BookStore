package com.kang.bean;

import java.util.List;

/**
 * @author Kangshitao
 * @date 2021年6月13日 下午9:50
 */

/**
 * page是分页的模型对象
 * @param <T> 是具体的模块的javaBean类
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    private Integer pageNo; //current page number
    private Integer pageTotal; //total page number
    private Integer pageSize = PAGE_SIZE; //number of per page
    private Integer pageTotalCount;  // total items number
    private List<T> items;  //current page data
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        //设置边界检查
        if(pageNo < 1){
            this.pageNo = 1;
        }else if(pageNo > this.pageTotal){
            this.pageNo = this.pageTotal;
        }else{
            this.pageNo = pageNo;
        }
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
