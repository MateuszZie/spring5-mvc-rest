package org.mateuszziebura.spring5mvcrest.api.v1.mapper;

import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5mvcrest.api.v1.model.CategoryDTO;
import org.mateuszziebura.spring5mvcrest.domain.Category;


import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        System.out.println(categoryDTO.getId() +" "+ categoryDTO.getName());
        assertNotNull(categoryDTO);
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}