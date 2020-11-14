package com.school.sunflower.util.mapper;


import com.school.sunflower.model.dto.ClassDTO;
import com.school.sunflower.model.dto.InvoiceDTO;
import com.school.sunflower.model.entity.Class;
import com.school.sunflower.model.payload.request.ClassRequest;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    ClassDTO toDto(Class classEntity);
    List<ClassDTO> toDtoList(List<Class> classEntities);
    List<ClassDTO> toDtoList(Set<Class> classEntities);

    @Mappings({
            @Mapping(target = "cost", source = "classEntity.course.cost"),
            @Mapping(target = "classDTO", source = "classEntity"),
    })
    InvoiceDTO toInvoiceDto(Class classEntity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Class toEntity(ClassRequest classRequest);

    @InheritConfiguration
    Class toEntity(ClassRequest classEditRequest, @MappingTarget Class existClass);

}
