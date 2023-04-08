package com.younggal.project.common.dto;

import com.younggal.project.enumType.FileType;
import lombok.Data;

@Data
public class AttachmentInfo {
    private FileType fileType;
    private String fileOrgName;
    private String fileName;
    private String filePath;
    private String fileExtension;
    private Integer width;
    private Integer height;
}
