package com.school.sunflower.util.mapper;

import com.school.sunflower.model.dto.CourseDTO;
import com.school.sunflower.model.dto.StudentDTO;
import com.school.sunflower.model.entity.Course;
import com.school.sunflower.model.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO toDto(Student student);
    List<StudentDTO> toDtoList(List<Student> students);

}
