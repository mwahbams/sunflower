package com.school.sunflower.service;

import com.school.sunflower.model.dto.CourseDTO;
import com.school.sunflower.model.dto.PaginationDTO;
import com.school.sunflower.model.payload.request.CourseRequest;
import com.school.sunflower.model.payload.request.criteria.PaginationCriteria;

public interface CourseService {
    PaginationDTO findAllCourses(PaginationCriteria paginationCriteria);

    CourseDTO findCourseById(Long courseId);

    void deleteCourse(Long courseId);

    CourseDTO addCourse(CourseRequest courseRequest);

    CourseDTO updateCourse(Long focusAreaId, CourseRequest courseRequest);

    PaginationDTO findAllCoursesMappedToClasses(PaginationCriteria paginationCriteria);
}
