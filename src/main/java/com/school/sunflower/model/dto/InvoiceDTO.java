package com.school.sunflower.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class InvoiceDTO extends BaseEntityDTO {

    private ClassDTO classDTO;
    private Double cost;
}
