package com.younggal.project.category.service;

import com.younggal.project.category.dto.CategoryCreateDto;
import com.younggal.project.category.dto.CategorySortDto;
import com.younggal.project.category.dto.CategoryUpdateDto;
import com.younggal.project.category.dto.response.CategoryInfo;
import com.younggal.project.category.dto.response.CategoryWithSubCategory;
import com.younggal.project.category.repository.CategoryRepository;
import com.younggal.project.domain.category.Category;
import com.younggal.project.exception.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryBannerService categoryBannerService;

    private ModelMapper modelMapper = new ModelMapper();

    private final Sort sort = Sort.by(Sort.Order.asc("sort"));

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository, categoryBannerService, modelMapper);
    }

    @Test
    @DisplayName("createCategory 테스트")
    public void createCategoryTest() {
        CategoryCreateDto dto = new CategoryCreateDto();
        dto.setName("testName");
        dto.setUrl("testUrl");
        dto.setIsActive(true);
        dto.setParentId(null);

        Category savedCategory = new Category();
        savedCategory.setId(1L);
        savedCategory.setName("testName");
        savedCategory.setUrl("testUrl");
        savedCategory.setIsActive(true);
        savedCategory.setParentId(null);
        savedCategory.setDepth(1);
        savedCategory.setSort(1);

        when(categoryRepository.findByDepthAndIsDelete(1,false, sort)).thenReturn(new ArrayList<>());
        when(categoryRepository.save(any(Category.class))).thenReturn(savedCategory);

        CategoryInfo result = categoryService.createCategory(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(savedCategory.getId());
        assertThat(result.getName()).isEqualTo(savedCategory.getName());
        assertThat(result.getUrl()).isEqualTo(savedCategory.getUrl());
        assertThat(result.getIsActive()).isEqualTo(savedCategory.getIsActive());
    }
}
