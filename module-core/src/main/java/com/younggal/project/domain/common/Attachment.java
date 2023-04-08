package com.younggal.project.domain.common;

import com.younggal.project.enumType.FileType;
import com.younggal.project.enumType.UsedType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "ATTACHMENT")
@Data
@Schema(description = "공통 이미지")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "이미지 ID")
    private Long id;

    @Column(name = "used_type", length = 30, nullable = false)
    @Enumerated(STRING)
    @Schema(description = "사용 구분")
    private UsedType usedType;

    @Column(name = "file_type", length = 10, nullable = false)
    @Enumerated(STRING)
    @Schema(description = "파일 구분")
    private FileType fileType;

    @Column(name = "file_org_name", length = 255, nullable = false)
    @Schema(description = "원본 파일 이름")
    private String fileOrgName;

    @Column(name = "file_name", length = 255, nullable = false)
    @Schema(description = "저장 파일 이름")
    private String fileName;

    @Column(name = "file_path", length = 1024, nullable = false)
    @Schema(description = "저장 파일 경로")
    private String filePath;

    @Column(name = "file_extension", length = 50, nullable = false)
    @Schema(description = "파일 확장자")
    private String fileExtension;

    @Column(name = "width")
    @Schema(description = "너비 (px)")
    private Integer width;

    @Column(name = "height")
    @Schema(description = "높이 (px)")
    private Integer height;

    @Column(name = "size")
    @Schema(description = "파일 크기 (byte)")
    private Long size;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    @Schema(description = "생성 일시")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Schema(description = "수정 일시")
    private LocalDateTime updatedAt;
}
