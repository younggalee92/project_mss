package com.younggal.project.category.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CategoryBannerSortDto {
    @NotEmpty
    private List<CategoryBannerSort> sortedList;

    @Data
    public static class CategoryBannerSort {
        @NotNull
        private long categoryBannerId;
        @NotNull
        private int sort;
    }
}
