package com.younggal.project.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryUpdateDto {
    @NotNull
    private long categoryId;
    @NotBlank
    private String name;
    private String url;
    private Boolean isActive = true;
}
