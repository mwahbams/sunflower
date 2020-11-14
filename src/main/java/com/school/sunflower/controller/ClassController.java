package com.school.sunflower.controller;

import com.school.sunflower.model.dto.ClassDTO;
import com.school.sunflower.model.dto.InvoiceDTO;
import com.school.sunflower.model.dto.PaginationDTO;
import com.school.sunflower.model.payload.request.ApiResponse;
import com.school.sunflower.model.payload.request.ClassRequest;
import com.school.sunflower.model.payload.request.criteria.PaginationCriteria;
import com.school.sunflower.service.ClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.ValidationException;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
@Api(tags = "class")
public class ClassController {

    private final ClassService classService;

    /**
     *  find All Available Classes
     * @param paginationCriteria
     * @return
     */
    @GetMapping
    @ApiOperation(value = "find All Available Classes")
    public ResponseEntity<ApiResponse<PaginationDTO>> findAllAvailableClasses(@Valid PaginationCriteria paginationCriteria) {
        PaginationDTO classes = classService.findAllAvailableClasses(paginationCriteria);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(classes));
    }

    /**
     * Create a new ‘Class’ request by assigning a teacher to a course
     * @param classRequest
     * @return
     */
    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation(value = "add Class")
    public ApiResponse<ClassDTO> addClass(@Valid @RequestBody ClassRequest classRequest) {
        return ApiResponse.created(classService.addClass(classRequest));
    }

    /**
     * Avail Class for specific course for student registration
     * Register student to class of a mapped course, no more than 3
     * @param classId
     * @param email
     * @return
     * @throws ValidationException
     */
    @PostMapping("/{classId}/student-register")
    @ApiOperation(value = "register Student To Class")
    public ApiResponse<InvoiceDTO> registerStudentToClass(@PathVariable Long classId, @NotNull @RequestHeader String email) throws ValidationException {
        InvoiceDTO invoiceDTO =classService.registerStudentToClass(classId, email);
        return ApiResponse.ok(invoiceDTO);
    }

    /**
     * Close class
     * @param classId
     * @return
     */
    @PostMapping("/{classId}/close")
    @ResponseStatus(CREATED)
    @ApiOperation(value = "register Student To Class")
    public ApiResponse<Void> closeClass(@PathVariable Long classId) {
        classService.closeClass(classId);
        return ApiResponse.created(null);
    }
}
