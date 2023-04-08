package com.younggal.project.category.service;

import com.younggal.project.category.dto.CategoryBannerCreateDto;
import com.younggal.project.category.dto.CategoryBannerSortDto;
import com.younggal.project.category.dto.CategoryBannerUpdateDto;
import com.younggal.project.category.dto.response.CategoryBannerInfo;
import com.younggal.project.category.repository.CategoryBannerRepository;
import com.younggal.project.category.repository.CategoryRepository;
import com.younggal.project.common.service.AttachmentService;
import com.younggal.project.domain.category.Category;
import com.younggal.project.domain.category.CategoryBanner;
import com.younggal.project.domain.common.Attachment;
import com.younggal.project.exception.CategoryBannerNotFoundException;
import com.younggal.project.exception.CategoryNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryBannerService {
    private final CategoryBannerRepository categoryBannerRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentService attachmentService;
    private final ModelMapper modelMapper;

    private final Sort sort = Sort.by(Sort.Order.asc("sort"));

    public CategoryBannerInfo createCategoryBanner(CategoryBannerCreateDto dto) {
        Category category = categoryRepository.findByIdAndIsDelete(dto.getCategoryId(), false)
                .orElseThrow(() -> new CategoryNotFoundException("카테고리를 찾을 수 없습니다."));
        Attachment attachment = attachmentService.validationAttachment(dto.getAttachmentId());

        CategoryBanner banner = new CategoryBanner();
        banner.setCategory(category);
        banner.setAttachment(attachment);
        banner.setBannerUrl(dto.getBannerUrl());
        banner.setMemo(dto.getMemo());
        banner.setIsActive(dto.getIsActive());
        banner.setSort(category.getBannerList().get(category.getBannerList().size() - 1).getSort() + 1);

        categoryBannerRepository.save(banner);

        return modelMapper.map(banner, CategoryBannerInfo.class);
    }

    public void updateCategoryBanner(CategoryBannerUpdateDto dto) {
        CategoryBanner banner = validationCategoryBanner(dto.getCategoryBannerId());
        Attachment attachment = attachmentService.validationAttachment(dto.getAttachmentId());

        banner.setAttachment(attachment);
        banner.setBannerUrl(dto.getBannerUrl());
        banner.setMemo(dto.getMemo());
        banner.setIsActive(dto.getIsActive());

        categoryBannerRepository.save(banner);
    }

    @Transactional
    public void deleteCategoryBanner(Long categoryBannerId) {
        CategoryBanner deleteBanner = validationCategoryBanner(categoryBannerId);
        deleteBanner.setIsDelete(true);

        categoryBannerRepository.save(deleteBanner);

        // 동일 카테고리의 배너 순서 재정렬
        List<CategoryBanner> bannerList = getCategoryBannerList(deleteBanner.getCategory().getId());

        bannerList.stream()
                .forEach(banner -> {
                    banner.setSort(bannerList.indexOf(banner) + 1);
                    categoryBannerRepository.save(banner);
                });
    }

    public void deleteCategoryBannerList(Long categoryId) {
        List<CategoryBanner> bannerList = getCategoryBannerList(categoryId);
        bannerList.stream()
                .filter(banner -> !banner.getIsDelete()) // isDelete가 false인 값만 필터링
                .forEach(banner -> banner.setIsDelete(true));

        categoryBannerRepository.saveAll(bannerList);
    }

    public CategoryBannerInfo getCategoryBanner(Long categoryBannerId) {
        CategoryBanner banner = validationCategoryBanner(categoryBannerId);
        return modelMapper.map(banner, CategoryBannerInfo.class);
    }

    public List<CategoryBanner> getCategoryBannerList(Long categoryId) {
        return categoryBannerRepository.findByCategoryIdAndIsDelete(categoryId, false, sort);
    }

    public CategoryBanner validationCategoryBanner(Long categoryBannerId) {
        CategoryBanner banner = categoryBannerRepository.findByIdAndIsDelete(categoryBannerId, false)
                .orElseThrow(() -> new CategoryBannerNotFoundException("카테고리 배너를 찾을 수 없습니다."));
        return banner;
    }

    @Transactional
    public void sortCategoryBannerList(CategoryBannerSortDto dto) {
        List<Long> idList = dto.getSortedList().stream()
                .map(CategoryBannerSortDto.CategoryBannerSort::getCategoryBannerId)
                .collect(Collectors.toList());
        List<CategoryBanner> bannerList = categoryBannerRepository.findAllById(idList);

        dto.getSortedList().forEach(categoryBannerSort -> {
            bannerList.stream()
                    .filter(banner -> banner.getId() == categoryBannerSort.getCategoryBannerId())
                    .forEach(banner -> {
                        banner.setSort(categoryBannerSort.getSort());
                        categoryBannerRepository.save(banner);
                    });
        });
    }

}
