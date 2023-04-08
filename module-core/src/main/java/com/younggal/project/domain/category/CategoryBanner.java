package com.younggal.project.domain.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.younggal.project.domain.common.Attachment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CATEGORY_BANNER")
@Data
@DynamicInsert
@DynamicUpdate
@Schema(description = "카테고리 배너")
public class CategoryBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "배너 ID")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @Schema(description = "카테고리 ID")
    private Category category;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id", referencedColumnName = "id", nullable = false)
    @Schema(description = "파일 ID")
    private Attachment attachment;

    @Column(name = "banner_url")
    @Schema(description = "배너 URL")
    private String bannerUrl;

    @Column(name = "sort", nullable = false)
    @Schema(description = "정렬 순서")
    private Integer sort = 1;

    @Column(name = "memo", length = 200)
    @Schema(description = "관리자용 메모")
    private String memo;

    @Column(name = "is_active", nullable = false, columnDefinition="tinyint(1) default 1")
    @Schema(description = "활성화 여부")
    private Boolean isActive = true;

    @Column(name = "is_delete", nullable = false, columnDefinition="tinyint(1) default 0")
    @Schema(description = "삭제 여부")
    private Boolean isDelete = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    @Schema(description = "생성 일시")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Schema(description = "수정 일시")
    private LocalDateTime updatedAt;
}