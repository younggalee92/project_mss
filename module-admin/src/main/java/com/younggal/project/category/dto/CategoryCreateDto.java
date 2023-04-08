package com.younggal.project.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryCreateDto {
    private Long parentId;
    @NotBlank
    private String name;
    private String url;
    private Boolean isActive = true;
}
