package com.younggal.project.category.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryInfo {
    private Long id;
    private String name;
    private String url;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CategoryBannerInfo> bannerList;
}
