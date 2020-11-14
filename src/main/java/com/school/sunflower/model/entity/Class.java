package com.school.sunflower.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "class")
public class Class extends BaseEntity {


    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "is_open")
    private Boolean isOpen = Boolean.TRUE;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate ;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate ;

    @NotNull
    @Column(name = "period_in_weeks")
    private Long periodInWeeks;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;



    @JoinTable(name = "class_student", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    public void addStudent(Student student) {
        this.students.add(student);
//        student.getClasses().add(this);
    }

}
