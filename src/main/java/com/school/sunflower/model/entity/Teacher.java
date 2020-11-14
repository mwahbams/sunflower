package com.school.sunflower.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "teacher")
public class Teacher extends BaseEntity {

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

    @NotBlank
    @Column(name = "title")
    private String title;

    @Column(name = "courses_taught")
    private String coursesTaught;

//    @Lob
//    @Column(name = "image")
//    private byte[] image;


    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Class> classes = new ArrayList<>();

}
