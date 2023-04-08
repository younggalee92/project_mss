package com.younggal.project.category.api;

import com.younggal.project.category.dto.CategoryCreateDto;
import com.younggal.project.category.dto.CategorySortDto;
import com.younggal.project.category.dto.CategoryUpdateDto;
import com.younggal.project.category.dto.response.CategoryInfo;
import com.younggal.project.category.dto.response.CategoryWithSubCategory;
import com.younggal.project.category.service.CategoryService;
import com.younggal.project.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryCreateDto dto) {
        CategoryInfo categoryInfo = categoryService.createCategory(dto);
        return ResponseDto.setResponse(categoryInfo);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryUpdateDto dto) {
        CategoryInfo categoryInfo = categoryService.updateCategory(dto);
        return ResponseDto.setResponse(categoryInfo);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseDto.setResponse();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryInfo(@PathVariable long categoryId) {
        CategoryInfo categoryInfo = categoryService.getCategoryInfo(categoryId);
        return ResponseDto.setResponse(categoryInfo);
    }

    @GetMapping("/list/{depth}")
    public ResponseEntity<?> getCategoryList(@PathVariable int depth) {
        List<CategoryInfo> response = categoryService.getCategoryList(depth);
        return ResponseDto.setResponse(response);
    }

    @GetMapping("/{categoryId}/subcategory/list")
    public ResponseEntity<?> getSubcategoryList(@PathVariable long categoryId) {
        CategoryWithSubCategory response = categoryService.getSubcategoryList(categoryId);
        return ResponseDto.setResponse(response);
    }

    @PutMapping("/sort")
    public ResponseEntity<?> sortCategoryList(@Valid @RequestBody CategorySortDto dto) {
        categoryService.sortCategoryList(dto);
        return ResponseDto.setResponse();
    }

}
