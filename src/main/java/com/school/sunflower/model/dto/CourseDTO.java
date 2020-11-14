package com.school.sunflower.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ApiModel
public class CourseDTO extends BaseEntityDTO {

    private String arName;
    private String enName;
    private String enDescription;
    private String arDescription;
    private String prerequisite;
    private Long maxCapacity;
    private Double cost;
}
