package com.younggal.project.category.repository;

import com.younggal.project.domain.category.CategoryBanner;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryBannerRepository extends JpaRepository<CategoryBanner, Long> {
    Optional<CategoryBanner> findByIdAndIsDelete(Long categoryBannerId, boolean isDelete);
    List<CategoryBanner> findByCategoryIdAndIsDelete(Long categoryId, boolean isDelete, Sort sort);
}
