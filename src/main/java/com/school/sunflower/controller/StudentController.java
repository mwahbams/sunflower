package com.school.sunflower.controller;

import com.school.sunflower.model.dto.ClassDTO;
import com.school.sunflower.model.dto.PaginationDTO;
import com.school.sunflower.model.dto.StudentDTO;
import com.school.sunflower.model.payload.request.ApiResponse;
import com.school.sunflower.model.payload.request.criteria.PaginationCriteria;
import com.school.sunflower.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
@Api(tags = "student")
public class StudentController {


    private final StudentService studentService;

    /**
     * Get a list of all student
     * @param paginationCriteria
     * @return
     */
    @GetMapping
    @ApiOperation(value = "find All students")
    public ResponseEntity<ApiResponse<PaginationDTO>> findAllCourses(@Valid PaginationCriteria paginationCriteria) {
        PaginationDTO students = studentService.findAllStudents(paginationCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(students));
    }

    /**
     * Get all registered classes
     * @param studentId
     * @return
     */
    @GetMapping("/{studentId}/registered-classes/")
    @ApiOperation(value = "find All students")
    public ResponseEntity<ApiResponse<List<ClassDTO>>> findAllRegisteredClasses(@PathVariable Long studentId) {
        List<ClassDTO> students = studentService.findAllRegisteredClasses(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(students));
    }

    /**
     * Get a specific student by ID
     * @param studentId
     * @return
     */
    @GetMapping("/{studentId}")
    @ApiOperation(value = "Find Student By Id")
    public ResponseEntity<ApiResponse<StudentDTO>> findNewsById(@PathVariable Long studentId){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(studentService.findStudentById(studentId)));
    }
}
