package com.younggal.project.domain.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Table(name = "CATEGORY")
@Data
@DynamicInsert
@DynamicUpdate
@Schema(description = "카테고리 정보")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "카테고리 ID")
    private Long id;

    @Column(name = "parent_id", nullable = false)
    @Schema(description = "부모 카테고리 ID")
    private Long parentId;

    @Schema(description = "카테고리명")
    @Column(length = 50, nullable = false)
    private String name;

    @Schema(description = "카테고리 링크")
    @Column(length = 1000, nullable = false)
    private String url;

    @Schema(description = "하위 depth")
    @Column(nullable = false)
    private Integer depth = 1;

    @Column(name = "sort", nullable = false)
    @Schema(description = "정렬 순서")
    private Integer sort = 1;

    @Column(name = "is_active", columnDefinition="tinyint(1) default 1", nullable = false)
    @Schema(description = "활성화여부")
    private Boolean isActive = true;

    @Column(name = "is_delete", columnDefinition="tinyint(1) default 0", nullable = false)
    @Schema(description = "삭제여부")
    private Boolean isDelete = false;

    @CreationTimestamp
    @Column(name = "created_at")
    @Schema(description = "생성일시")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Schema(description = "수정일시")
    private LocalDateTime updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Where(clause = "is_delete = false")
    @OrderBy("sort ASC")
    private List<Category> children = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private Category parent;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @Fetch(FetchMode.SUBSELECT)
    private List<Category> allChildren = new ArrayList<>();

    public List<Category> getAllChildren() {
        return allChildren;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @Where(clause = "is_delete = false")
    @OrderBy("sort ASC")
    private List<CategoryBanner> bannerList;

}