package dev.kirin.toy.lottoweb.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Pageable<T> {
    private int totalPage;
    private long totalCount;
    private int page;
    private int size;
    private List<T> contents;

    public static <T> Pageable<T> from(Page<?> pageable, List<T> contents) {
        return Pageable.<T>builder()
                .page(pageable.getNumber())
                .size(pageable.getSize())
                .totalPage(pageable.getTotalPages())
                .totalCount(pageable.getTotalElements())
                .contents(contents)
                .build();
    }
}
