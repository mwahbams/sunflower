package com.school.sunflower.model.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CourseRequest {


    @NotBlank
    private String arName;
    private String enName;

    @NotBlank
    private String enDescription;

    @NotBlank
    private String arDescription;

    private String prerequisite;

    @NotNull
    private Long maxCapacity;
}
