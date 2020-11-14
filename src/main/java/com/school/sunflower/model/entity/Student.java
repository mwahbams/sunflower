package com.school.sunflower.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "student")
public class Student extends BaseEntity {

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "user_name", unique = true)
    private String userName;

    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

//    @Lob
//    @Column(name = "image")
//    private byte[] image;


    @ManyToMany(mappedBy="students",fetch = FetchType.LAZY)
    private Set<Class> classes = new HashSet<>();
}
