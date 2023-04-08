package com.younggal.project.category.api;

import com.younggal.project.category.dto.CategoryBannerCreateDto;
import com.younggal.project.category.dto.CategoryBannerSortDto;
import com.younggal.project.category.dto.CategoryBannerUpdateDto;
import com.younggal.project.category.dto.response.CategoryBannerInfo;
import com.younggal.project.category.service.CategoryBannerService;
import com.younggal.project.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/category/banner")
public class CategoryBannerController {
    private final CategoryBannerService categoryBannerService;

    @PostMapping
    public ResponseEntity<?> createCategoryBanner(@Valid @RequestBody CategoryBannerCreateDto dto) {
        CategoryBannerInfo bannerInfo = categoryBannerService.createCategoryBanner(dto);
        return ResponseDto.setResponse(bannerInfo);
    }

    @PutMapping
    public ResponseEntity<?> updateCategoryBanner(@Valid @RequestBody CategoryBannerUpdateDto dto) {
        categoryBannerService.updateCategoryBanner(dto);
        return ResponseDto.setResponse();
    }

    @DeleteMapping("/{categoryBannerId}")
    public ResponseEntity<?> deleteCategoryBanner(@PathVariable long categoryBannerId) {
        categoryBannerService.deleteCategoryBanner(categoryBannerId);
        return ResponseDto.setResponse();
    }

    @GetMapping("/{categoryBannerId}")
    public ResponseEntity<?> getCategoryBanner(@PathVariable long categoryBannerId) {
        CategoryBannerInfo bannerInfo = categoryBannerService.getCategoryBanner(categoryBannerId);
        return ResponseDto.setResponse(bannerInfo);
    }

    @PutMapping("/sort")
    public ResponseEntity<?> sortCategoryBannerList(@Valid @RequestBody CategoryBannerSortDto dto) {
        categoryBannerService.sortCategoryBannerList(dto);
        return ResponseDto.setResponse();
    }

}
