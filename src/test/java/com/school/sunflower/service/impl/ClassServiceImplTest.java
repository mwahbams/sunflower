package com.school.sunflower.service.impl;

import com.school.sunflower.model.entity.Class;
import com.school.sunflower.repository.ClassRepository;
import com.school.sunflower.service.ClassService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ClassServiceImplTest {

    @Autowired
    private ClassService classService;

    @MockBean
    private ClassRepository classRepository;

    @Test
    void testAddClass() {
    }

    @Test
    void testRegisterStudentToClass() {
    }

    @Test
    void testCloseClass() {
    }

    @Test
    @DisplayName("Test FindAll Available Classes")
    void testFindAllAvailableClasses() {
    }

}