package com.school.sunflower.service.impl;


import com.school.sunflower.common.exception.RecordNotFoundException;
import com.school.sunflower.model.dto.ClassDTO;
import com.school.sunflower.model.dto.StudentDTO;
import com.school.sunflower.model.dto.PaginationDTO;
import com.school.sunflower.model.dto.StudentDTO;
import com.school.sunflower.model.entity.Student;
import com.school.sunflower.model.entity.Student;
import com.school.sunflower.model.payload.request.criteria.PaginationCriteria;
import com.school.sunflower.repository.ClassRepository;
import com.school.sunflower.repository.StudentRepository;
import com.school.sunflower.service.ClassService;
import com.school.sunflower.service.StudentService;
import com.school.sunflower.util.Constants;
import com.school.sunflower.util.mapper.ClassMapper;
import com.school.sunflower.util.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final ClassMapper classMapper;
    private final StudentRepository studentRepository;

    @Override
    public PaginationDTO findAllStudents(PaginationCriteria paginationCriteria) {
        PageRequest pageRequest = PageRequest.of(paginationCriteria.getOffset(), paginationCriteria.getLimit());
        Page<Student> studentPage = studentRepository.findAll(pageRequest);
        return prepareStudentPagination(studentPage);
    }

    @Override
    public StudentDTO findStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RecordNotFoundException(Constants.ErrorKeys.VC_STUDENT_NOT_FOUND));
        return studentMapper.toDto(student);
    }

    @Override
    public List<ClassDTO> findAllRegisteredClasses(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RecordNotFoundException(Constants.ErrorKeys.VC_STUDENT_NOT_FOUND));
        List<ClassDTO> classDTOS = classMapper.toDtoList(student.getClasses());
        return classDTOS;
    }


    private PaginationDTO prepareStudentPagination(Page<Student> studentPage) {
        List<StudentDTO> studentDTOs = studentMapper.toDtoList(studentPage.getContent());
        return PaginationDTO.builder()
                .totalElements(studentPage.getTotalElements())
                .totalPages(studentPage.getTotalPages())
                .data(studentDTOs)
                .build();
    }
}
