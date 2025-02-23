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

    public static <T> PageResult<T> fromPage(Page<T> page) {
        final var result = new PageResult<T>(0, 1);
        if (Objects.nonNull(page)) {
            result.setPageNo(page.getNumber());
            result.setPageSize(page.getSize());
            result.getResults().addAll(page.getContent());
        }
        return result;
    }

    public static <Src, Dest> PageResult<Dest> fromPage(Page<Src> page, Function<? super Src, ? extends Dest> convertFunc) {
        final var result = new PageResult<Dest>(0, 1);
        if (Objects.nonNull(page)) {
            result.setPageNo(page.getNumber());
            result.setPageSize(page.getSize());
            if (Objects.nonNull(convertFunc)) {
                result.getResults().addAll(page.get().map(convertFunc).toList());
            }
        }
        return result;
    }

    public static <Src, Dest> PageResult<Dest> fromPageResult(PageResult<Src> pageResult, Function<? super Src, ? extends Dest> convertFunc) {
        final var result = new PageResult<Dest>(0, 1);
        if (Objects.nonNull(pageResult)) {
            result.setPageNo(pageResult.getPageNo());
            result.setPageSize(pageResult.getPageSize());
            if (Objects.nonNull(convertFunc)) {
                result.getResults().addAll(pageResult.getResults().stream().map(convertFunc).toList());
            }
        }
        return result;
    }
}
