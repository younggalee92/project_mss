package com.younggal.project.category.service;

import com.younggal.project.domain.category.Category;
import com.younggal.project.category.dto.response.CategoryInfo;
import com.younggal.project.category.dto.response.CategoryWithSubCategory;
import com.younggal.project.category.repository.CategoryRepository;
import com.younggal.project.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryInfo getCategoryInfo(Long categoryId) {
        Category category = validationCategory(categoryId);
        if (category.getDepth() == 1) {
            category.getBannerList();
        }
        return modelMapper.map(category, CategoryInfo.class);
    }

    public List<CategoryInfo> getCategoryList(int depth) {
        List<Category> categoryList = categoryRepository.findByDepthAndIsDelete(depth,false);
        return categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryInfo.class))
                .collect(Collectors.toList());
    }

    public CategoryWithSubCategory getSubcategoryList(Long categoryId) {
        Category category = validationCategory(categoryId);
        category.getAllChildren();
        return modelMapper.map(category, CategoryWithSubCategory.class);
    }

    public Category validationCategory(Long categoryId) {
        Category category = categoryRepository.findByIdAndIsDelete(categoryId, false)
                .orElseThrow(() -> new CategoryNotFoundException("카테고리를 찾을 수 없습니다."));
        return category;
    }

}
