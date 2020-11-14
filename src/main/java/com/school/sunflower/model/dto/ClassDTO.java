package com.school.sunflower.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.school.sunflower.model.entity.Course;
import com.school.sunflower.model.entity.Teacher;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ApiModel
public class ClassDTO extends BaseEntityDTO{

    private String name;
    private LocalDate startDate ;
    private LocalDate endDate ;
    private Long periodInWeeks;
    private Boolean isOpen;
    private CourseDTO course;
    private TeacherDTO teacher;
}
