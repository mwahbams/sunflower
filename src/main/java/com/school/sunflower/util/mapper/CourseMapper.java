package com.school.sunflower.util.mapper;

import com.school.sunflower.model.dto.CourseDTO;
import com.school.sunflower.model.entity.Course;
import com.school.sunflower.model.payload.request.CourseRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {


    CourseDTO toDto(Course course);
    List<CourseDTO> toDtoList(List<Course> courses);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Course toEntity(CourseRequest courseRequest);

    @InheritConfiguration
    @Mappings({
            @Mapping(target = "maxCapacity", ignore = true),
    })
    Course toEntity(CourseRequest courseEditRequest, @MappingTarget Course existCourse);
    
}
