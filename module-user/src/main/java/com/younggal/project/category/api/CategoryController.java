package com.younggal.project.category.api;

import com.younggal.project.category.dto.response.CategoryInfo;
import com.younggal.project.category.dto.response.CategoryWithSubCategory;
import com.younggal.project.category.service.CategoryService;
import com.younggal.project.dto.ResponseDto;
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

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryInfo(@PathVariable Long categoryId) {
        CategoryInfo categoryInfo = categoryService.getCategoryInfo(categoryId);
        return ResponseDto.setResponse(categoryInfo);
    }

    @GetMapping("/list/{depth}")
    public ResponseEntity<?> getCategoryList(@PathVariable int depth) {
        List<CategoryInfo> response = categoryService.getCategoryList(depth);
        return ResponseDto.setResponse(response);
    }

    @GetMapping("/{categoryId}/subcategory/list")
    public ResponseEntity<?> getSubcategoryList(@PathVariable Long categoryId) {
        CategoryWithSubCategory response = categoryService.getSubcategoryList(categoryId);
        return ResponseDto.setResponse(response);
    }
}
