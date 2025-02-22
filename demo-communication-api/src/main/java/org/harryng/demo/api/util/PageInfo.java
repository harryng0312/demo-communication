package org.harryng.demo.api.util;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

@Data
@Getter
public class PageInfo implements Serializable {
    private int pageNo = 0;
    private int pageSize = 1;

    public PageInfo() {
    }

    public PageInfo(int pageNo, int pageSize) {
        this.pageNo = Math.max(0, pageNo);
        this.pageSize = Math.max(1, pageSize);
    }

    public void setPageNo(int pageNo) {
        pageNo = Math.max(0, pageNo);
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        pageSize = Math.max(1, pageSize);
        this.pageSize = pageSize;
    }

    public long getStartRowIndex() {
        return (long) getPageNo() * getPageSize();
    }

    public Pageable toPageable() {
        return PageRequest.of(getPageNo(), getPageSize());
    }
}
