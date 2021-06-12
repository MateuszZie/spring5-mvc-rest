package org.mateuszziebura.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mateuszziebura.spring5mvcrest.api.v1.model.CategoryDTO;
import org.mateuszziebura.spring5mvcrest.domain.Category;
import org.springframework.context.annotation.Bean;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
