package org.harryng.demo.api.util;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PageResult<R> extends PageInfo {
    @Setter(AccessLevel.NONE)
    private List<R> results = new ArrayList<>();

    public PageResult(PageInfo pageInfo) {
        super(pageInfo.getPageNo(), pageInfo.getPageSize());
    }

    public PageResult(int startPageIndex, int pageSize) {
        super(startPageIndex, pageSize);
    }

    public void fromPage(Page<R> page) {
        if (Objects.nonNull(page)) {
            this.setPageNo(page.getNumber());
            this.setPageSize(page.getSize());
            this.getResults().addAll(page.getContent());
        }
    }

    public <T> void fromPage(Page<T> page, Function<? super T, ? extends R> convertFunc) {
        if (Objects.nonNull(page)) {
            this.setPageNo(page.getNumber());
            this.setPageSize(page.getSize());
            if (Objects.nonNull(convertFunc)) {
                this.getResults().addAll(page.get().map(convertFunc).toList());
            }
        }
    }
}
