package com.school.sunflower.controller;

import com.school.sunflower.model.dto.CourseDTO;
import com.school.sunflower.model.dto.PaginationDTO;
import com.school.sunflower.model.payload.request.ApiResponse;
import com.school.sunflower.model.payload.request.CourseRequest;
import com.school.sunflower.model.payload.request.criteria.PaginationCriteria;
import com.school.sunflower.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
@Api(tags = "course")
public class CourseControllert {

    private final CourseService courseService;

    /**
     * Get a list of all courses with pagination
     * @param paginationCriteria
     * @return
     */
    @GetMapping
    @ApiOperation(value = "find All Courses")
    public ResponseEntity<ApiResponse<PaginationDTO>> findAllCourses(@Valid PaginationCriteria paginationCriteria) {
        PaginationDTO courses = courseService.findAllCourses(paginationCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(courses));
    }

    /**
     * Get a list of all available/open courses, mapped to classes with pagination
     * @param paginationCriteria
     * @return
     */
    @GetMapping("/mapped-classes")
    @ApiOperation(value = "find All Courses mapped Classes")
    public ResponseEntity<ApiResponse<PaginationDTO>> findAllCoursesMappedToClasses(@Valid PaginationCriteria paginationCriteria) {
        PaginationDTO courses = courseService.findAllCoursesMappedToClasses(paginationCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(courses));
    }


    /**
     * Get a specific course by ID
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}")
    @ApiOperation(value = "Find Course By Id")
    public ResponseEntity<ApiResponse<CourseDTO>> findNewsById(@PathVariable Long courseId){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(courseService.findCourseById(courseId)));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation(value = "add FocusArea")
    public ApiResponse<CourseDTO> create(@Valid @RequestBody CourseRequest courseRequest) {
        return ApiResponse.created(courseService.addCourse(courseRequest));
    }

    @PutMapping(value = "/{courseId}")
    @ApiOperation(value = "Update FocusArea by focusAreaId")
    public ApiResponse<CourseDTO> updateFocusArea(@PathVariable Long courseId, @Valid @RequestBody CourseRequest courseRequest) {
        return ApiResponse.ok(courseService.updateCourse(courseId, courseRequest));
    }

    @DeleteMapping("/{courseId}")
    @ApiOperation(value = "Delete Course")
    public ApiResponse<Void> deleteNews(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ApiResponse.ok(null);
    }
}
