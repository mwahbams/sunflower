package com.school.sunflower.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationDTO<T> {

    private T data ;
    private Long totalElements;
    private Integer totalPages;
}
