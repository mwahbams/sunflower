package com.school.sunflower.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "course")
public class Course extends BaseEntity {

    @NotBlank
    @Column(name = "ar_name")
    private String arName;

    @NotBlank
    @Column(name = "en_name")
    private String enName;

    @NotBlank
    @Size(max = 2000)
    @Column(name = "en_description", length = 2000)
    private String enDescription;


    @NotBlank
    @Size(max = 2000)
    @Column(name = "ar_description", length = 2000)
    private String arDescription;

    @NotBlank
    @Size(max = 2000)
    @Column(name = "prerequisite", length = 2000)
    private String prerequisite;

    @Column(name = "is_available")
    private Boolean isAvailable = Boolean.TRUE;

    @NotNull
    @Column(name = "max_capacity")
    private Long maxCapacity;

    @NotNull
    @Column(name = "cost")
    private Double cost;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Class> classes = new ArrayList<>();
}
