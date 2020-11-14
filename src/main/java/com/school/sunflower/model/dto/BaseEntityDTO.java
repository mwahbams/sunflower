package com.school.sunflower.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.school.sunflower.model.entity.Course;
import com.school.sunflower.model.entity.Teacher;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel
public class BaseEntityDTO {

    @ApiModelProperty(accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
