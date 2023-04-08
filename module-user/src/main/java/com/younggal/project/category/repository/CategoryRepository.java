package com.younggal.project.category.repository;

import com.younggal.project.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByIdAndIsDelete(Long categoryId, boolean isDelete);
    List<Category> findByDepthAndIsDelete(int depth, boolean isDelete);
}
