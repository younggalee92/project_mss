package com.younggal.project.category.service;

import com.younggal.project.category.dto.CategoryCreateDto;
import com.younggal.project.category.dto.CategorySortDto;
import com.younggal.project.category.dto.CategoryUpdateDto;
import com.younggal.project.category.dto.response.CategoryInfo;
import com.younggal.project.category.dto.response.CategoryWithSubCategory;
import com.younggal.project.category.repository.CategoryRepository;
import com.younggal.project.domain.category.Category;
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
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryBannerService categoryBannerService;
    private final ModelMapper modelMapper;

    private final Sort sort = Sort.by(Sort.Order.asc("sort"));

    public CategoryInfo createCategory(CategoryCreateDto dto) {
        Category category = new Category();

        category.setName(dto.getName());
        category.setUrl(dto.getUrl());
        category.setIsActive(dto.getIsActive());

        if (dto.getParentId() != null) {
            category.setParentId(dto.getParentId());

            // parentId 가 유효한지 검사하고, 해당 id의 depth는 몇인지 가져오기
            Category parentCategory = validationCategory(dto.getParentId());
            category.setDepth(parentCategory.getDepth() + 1);

            // parentId 의 그 아래 하위 메뉴 중 마지막 메뉴의 sort + 1
            List<Category> parentCategoryChildren = parentCategory.getChildren();
            category.setSort(parentCategoryChildren.get(parentCategoryChildren.size() - 1).getSort() + 1);
        } else {
            category.setDepth(1);
            List<Category> topCategoryList = categoryRepository.findByDepthAndIsDelete(1,false, sort);
            // 최상위 메뉴 중 마지막 메뉴의 sort + 1
            category.setSort(topCategoryList.get(topCategoryList.size() - 1).getSort() + 1);
        }

        category = categoryRepository.save(category);

        return modelMapper.map(category, CategoryInfo.class);
    }

    public CategoryInfo updateCategory(CategoryUpdateDto dto) {
        Category category = validationCategory(dto.getCategoryId());

        category.setName(dto.getName());
        category.setUrl(dto.getUrl());
        category.setIsActive(dto.getIsActive());
        categoryRepository.save(category);

        return modelMapper.map(category, CategoryInfo.class);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category deleteCategory = validationCategory(categoryId);

        // 하위 카테고리 모두 삭제
        List<Category> categoryList = categoryRepository.findByParentIdAndIsDelete(categoryId, false, sort);
        categoryList.add(deleteCategory);

        categoryList.stream()
                .filter(category -> !category.getIsDelete()) // isDelete가 false인 값만 필터링
                .forEach(category -> category.setIsDelete(true));

        // 최상위 카테고리일 경우, 하위 배너도 모두 삭제
        if (deleteCategory.getDepth() == 1) {
            categoryBannerService.deleteCategoryBannerList(categoryId);
        }

        categoryRepository.saveAll(categoryList);

        // 부모 카테고리의 하위 카테고리 정렬 다시하기
        List<Category> parentCatrgoryChildrens = categoryRepository.findByParentIdAndIsDelete(deleteCategory.getParentId(), false, sort);

        parentCatrgoryChildrens.stream()
                .forEach(child -> {
                    child.setSort(parentCatrgoryChildrens.indexOf(child) + 1);
                    categoryRepository.save(child);
                });
    }

    public CategoryInfo getCategoryInfo(Long categoryId) {
        Category category = validationCategory(categoryId);
        if (category.getDepth() == 1) {
            category.getBannerList();
        }
        return modelMapper.map(category, CategoryInfo.class);
    }

    public List<CategoryInfo> getCategoryList(int depth) {
        List<Category> categoryList = categoryRepository.findByDepthAndIsDelete(depth, false, sort);
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

    @Transactional
    public void sortCategoryList(CategorySortDto dto) {
        List<Long> idList = dto.getSortedList().stream()
                .map(CategorySortDto.CategorySort::getCategoryId)
                .collect(Collectors.toList());
        List<Category> categoryList = categoryRepository.findAllById(idList);

        dto.getSortedList().forEach(categorySort -> {
            categoryList.stream()
                    .filter(category -> category.getId() == categorySort.getCategoryId())
                    .forEach(category -> {
                        validationCategory(categorySort.getCategoryId());
                        category.setParentId(categorySort.getParentId());
                        category.setSort(categorySort.getSort());
                        category.setDepth(categorySort.getDepth());
                        categoryRepository.save(category);
                    });
        });
    }

}
