package com.school.sunflower.service;

import com.school.sunflower.model.dto.ClassDTO;
import com.school.sunflower.model.dto.PaginationDTO;
import com.school.sunflower.model.dto.StudentDTO;
import com.school.sunflower.model.entity.Student;
import com.school.sunflower.model.payload.request.criteria.PaginationCriteria;

import java.util.List;

public interface StudentService {
    PaginationDTO findAllStudents(PaginationCriteria paginationCriteria);

    StudentDTO findStudentById(Long studentId);

    List<ClassDTO> findAllRegisteredClasses(Long studentId);
}
