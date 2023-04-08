package com.younggal.project.category.dto.response;

import com.younggal.project.common.dto.AttachmentInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryBannerInfo {
    private Long id;
    private AttachmentInfo attachment;
    private String bannerUrl;
    private String memo;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
