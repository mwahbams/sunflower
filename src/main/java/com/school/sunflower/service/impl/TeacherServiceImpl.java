package com.school.sunflower.service.impl;


import com.school.sunflower.repository.CourseRepository;
import com.school.sunflower.repository.TeacherRepository;
import com.school.sunflower.service.CourseService;
import com.school.sunflower.service.TeacherService;
import com.school.sunflower.util.mapper.CourseMapper;
import com.school.sunflower.util.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;
    private final TeacherRepository teacherRepository;

}
