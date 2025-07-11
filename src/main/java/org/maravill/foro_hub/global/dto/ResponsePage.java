package org.maravill.foro_hub.global.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record ResponsePage<T>(List<T> content,
                              int page,
                              int size,
                              long totalElements,
                              int totalPages,
                              boolean last,
                              boolean first){
    public static <T> ResponsePage<T> from(Page<T> page) {
        return new ResponsePage<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst()
        );
    }
}
