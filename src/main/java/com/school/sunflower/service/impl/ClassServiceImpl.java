package com.school.sunflower.service.impl;


import com.school.sunflower.common.exception.RecordNotFoundException;
import com.school.sunflower.model.dto.ClassDTO;
import com.school.sunflower.model.dto.InvoiceDTO;
import com.school.sunflower.model.dto.PaginationDTO;
import com.school.sunflower.model.entity.Class;
import com.school.sunflower.model.entity.Course;
import com.school.sunflower.model.entity.Student;
import com.school.sunflower.model.entity.Teacher;
import com.school.sunflower.model.payload.request.ClassRequest;
import com.school.sunflower.model.payload.request.criteria.PaginationCriteria;
import com.school.sunflower.repository.ClassRepository;
import com.school.sunflower.repository.CourseRepository;
import com.school.sunflower.repository.StudentRepository;
import com.school.sunflower.repository.TeacherRepository;
import com.school.sunflower.service.ClassService;
import com.school.sunflower.util.Constants;
import com.school.sunflower.util.mapper.ClassMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassMapper classMapper;
    private final ClassRepository classRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    public ClassDTO addClass(ClassRequest classRequest) {
        log.info("starting addClass");

        Teacher teacher = teacherRepository.findById(classRequest.getTeacherId()).orElseThrow(() -> new RecordNotFoundException(Constants.ErrorKeys.VC_TEACHER_NOT_FOUND));
        Course course = courseRepository.findById(classRequest.getCourseId()).orElseThrow(() -> new RecordNotFoundException(Constants.ErrorKeys.VC_COURSE_NOT_FOUND));

        Class classEntity = classMapper.toEntity(classRequest);
        classEntity.setCourse(course);
        classEntity.setTeacher(teacher);

        Class savedClass = classRepository.save(classEntity);

        log.info("end addClass");

        return classMapper.toDto(savedClass);
    }

    @Override
    public InvoiceDTO registerStudentToClass(Long classId, String email) throws ValidationException {
        Class aClass = getaClass(classId);
        Student student = studentRepository.findByEmail(email.trim()).orElseThrow(() -> new RecordNotFoundException(Constants.ErrorKeys.VC_STUDENT_NOT_FOUND));
        if (student.getClasses().stream().count() >= 3)
            throw new ValidationException(Constants.ErrorKeys.MAXIMUM_THREE_COURSES);
        aClass.addStudent(student);
        classRepository.save(aClass);
        return classMapper.toInvoiceDto(aClass);
    }

    private Class getaClass(Long classId) {
        return classRepository.findById(classId).orElseThrow(() -> new RecordNotFoundException(Constants.ErrorKeys.VC_CLASS_NOT_FOUND));
    }

    @Override
    public void closeClass(Long classId) {
        Class aClass = getaClass(classId);
        aClass.setIsOpen(Boolean.FALSE);
        classRepository.save(aClass);
    }

    @Override
    public PaginationDTO findAllAvailableClasses(PaginationCriteria paginationCriteria) {
        log.info("starting findAllAvailableClasses");
        PageRequest pageRequest = PageRequest.of(paginationCriteria.getOffset(), paginationCriteria.getLimit());
        Page<Class> classPage = classRepository.findAllByIsOpenTrue(pageRequest);
        return prepareClassPagination(classPage);
    }

    private PaginationDTO prepareClassPagination(Page<Class> classPage) {
        List<ClassDTO> classDTOs = classMapper.toDtoList(classPage.getContent());
        return PaginationDTO.builder()
                .totalElements(classPage.getTotalElements())
                .totalPages(classPage.getTotalPages())
                .data(classDTOs)
                .build();
    }
}
