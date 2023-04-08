package com.younggal.project.category.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryBannerUpdateDto {
    @NotNull
    private long categoryBannerId;
    @NotNull
    private long attachmentId;
    private String bannerUrl;
    private String memo;
    private Boolean isActive = true;
}
