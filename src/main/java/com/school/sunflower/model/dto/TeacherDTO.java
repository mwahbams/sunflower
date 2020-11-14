package com.school.sunflower.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@ApiModel
public class TeacherDTO extends BaseEntityDTO {

    private String firstName;
    private String lastName;
    private String title;
    private String coursesTaught;
    private String email;
    private String userName;
}
