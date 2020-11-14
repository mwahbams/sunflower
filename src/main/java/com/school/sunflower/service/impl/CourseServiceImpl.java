package com.school.sunflower.service.impl;


import com.school.sunflower.common.exception.RecordNotFoundException;
import com.school.sunflower.model.dto.CourseDTO;
import com.school.sunflower.model.dto.PaginationDTO;
import com.school.sunflower.model.entity.Course;
import com.school.sunflower.model.payload.request.CourseRequest;
import com.school.sunflower.model.payload.request.criteria.PaginationCriteria;
import com.school.sunflower.repository.CourseRepository;
import com.school.sunflower.service.CourseService;
import com.school.sunflower.util.Constants;
import com.school.sunflower.util.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    @Override
    public PaginationDTO findAllCourses(PaginationCriteria paginationCriteria) {
        log.info("starting findAllCourses");
        Page<Course> coursePage = courseRepository.findAll(PageRequest.of(paginationCriteria.getOffset(), paginationCriteria.getLimit()));
        return prepareCoursePagination(coursePage);

    }

    @Override
    public PaginationDTO findAllCoursesMappedToClasses(PaginationCriteria paginationCriteria) {
        log.info("starting findAllCoursesMappedToClasses");
        Page<Course> coursePage = courseRepository.findAllCoursesMappedClasses(PageRequest.of(paginationCriteria.getOffset(), paginationCriteria.getLimit()));
        return prepareCoursePagination(coursePage);
    }

    @Override
    public CourseDTO findCourseById(Long courseId) {
        log.info("starting findCourseById");
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RecordNotFoundException(Constants.ErrorKeys.VC_COURSE_NOT_FOUND));
        return courseMapper.toDto(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RecordNotFoundException(Constants.ErrorKeys.VC_COURSE_NOT_FOUND));
        courseRepository.delete(course);
    }

    @Override
    public CourseDTO addCourse(CourseRequest courseRequest) {
        Course course = courseMapper.toEntity(courseRequest);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toDto(savedCourse);
    }

    @Override
    public CourseDTO updateCourse(Long courseId, CourseRequest courseRequest) {
        Course existCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new RecordNotFoundException(Constants.ErrorKeys.VC_COURSE_NOT_FOUND));
        Course course = courseMapper.toEntity(courseRequest, existCourse);
        Course updatedCourse = courseRepository.save(course);
        return courseMapper.toDto(updatedCourse);
    }

    private PaginationDTO prepareCoursePagination(Page<Course> coursePage) {
        List<CourseDTO> courseDTOs = courseMapper.toDtoList(coursePage.getContent());
        return PaginationDTO.builder()
                .totalElements(coursePage.getTotalElements())
                .totalPages(coursePage.getTotalPages())
                .data(courseDTOs)
                .build();
    }
}
