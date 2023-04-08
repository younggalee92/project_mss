package com.younggal.project.category.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CategorySortDto {
    @NotEmpty
    private List<CategorySort> sortedList;

    @Data
    public static class CategorySort {
        @NotNull
        private long categoryId;
        @NotNull
        private long parentId;
        @NotNull
        private int sort;
        @NotNull
        private int depth;
    }
}
