package com.school.sunflower.model.payload.request.criteria;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class PaginationCriteria {

    @Min(value = 0)
    @Max(value = 10)
    private int offset = 0;

    @Min(value = 1)
    @Max(value = 10)
    private int limit = 10;
}
