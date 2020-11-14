package com.school.sunflower.service;

import com.school.sunflower.model.dto.ClassDTO;
import com.school.sunflower.model.dto.InvoiceDTO;
import com.school.sunflower.model.dto.PaginationDTO;
import com.school.sunflower.model.payload.request.ClassRequest;
import com.school.sunflower.model.payload.request.criteria.PaginationCriteria;

import javax.xml.bind.ValidationException;

public interface ClassService {
    ClassDTO addClass(ClassRequest classRequest);

    InvoiceDTO registerStudentToClass(Long classId, String email) throws ValidationException;

    void closeClass(Long classId);

    PaginationDTO findAllAvailableClasses(PaginationCriteria paginationCriteria);
}
