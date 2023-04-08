package com.younggal.project.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "최상위 메뉴 배너 추가 DTO")
@Getter
@NoArgsConstructor
public class CategoryBannerCreateDto {
    @NotNull
    private long categoryId;
    @NotNull
    private long attachmentId;
    private String bannerUrl;
    private String memo;
    private Boolean isActive = true;
}
