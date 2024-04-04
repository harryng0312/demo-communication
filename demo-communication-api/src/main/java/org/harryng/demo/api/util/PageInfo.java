package org.harryng.demo.api.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public class PageInfo implements Serializable {
    private int startPageIndex = 0;
    private int pageSize = 1;

    public PageInfo() {
    }

    public PageInfo(int startPageIndex, int pageSize) {
        this.startPageIndex = startPageIndex;
        this.pageSize = pageSize;
    }

    public int getStartPageIndex() {
        return startPageIndex;
    }

    public void setStartPageIndex(int startPageIndex) {
        startPageIndex = Math.max(0, startPageIndex);
        this.startPageIndex = startPageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        pageSize = Math.max(1, pageSize);
        this.pageSize = pageSize;
    }

    public long getStartRowIndex() {
        return (long) getStartPageIndex() * getPageSize();
    }

    public Pageable toPageable() {
        return PageRequest.of(getStartPageIndex(), getPageSize());
    }
}
