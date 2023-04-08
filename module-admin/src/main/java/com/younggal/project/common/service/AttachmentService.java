package com.younggal.project.common.service;

import com.younggal.project.common.repository.AttachmentRepository;
import com.younggal.project.domain.common.Attachment;
import com.younggal.project.enumType.FileType;
import com.younggal.project.enumType.UsedType;
import com.younggal.project.exception.AttachmentNotFoundException;
import com.younggal.project.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;

    public Attachment uploadAttachment(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String filename = UUID.randomUUID().toString();
        Path path = Paths.get(filename);
        Files.write(path, bytes);
        Attachment attachment = new Attachment();
        attachment.setFileName(filename);
        attachment.setFileOrgName(file.getOriginalFilename());
        attachment.setUsedType(UsedType.CATEGORY_BANNER);
        attachment.setFileType(FileUtils.isImageOrVideo(file));
        attachment.setFileExtension(FileUtils.getFileFormat(file));
        attachment.setFilePath(path.toAbsolutePath().toString());
        attachment.setSize(file.getSize());
        if (FileUtils.isImageOrVideo(file) == FileType.IMAGE) {
            attachment.setHeight(FileUtils.getImageHeight(file));
            attachment.setWidth(FileUtils.getImageWidth(file));
        }
        attachment.setCreatedAt(LocalDateTime.now());
        attachmentRepository.save(attachment);

        return attachment;
    }

    public Attachment validationAttachment(Long attachmentId) {
        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new AttachmentNotFoundException("첨부파일을 찾을 수 없습니다."));
        return attachment;
    }
}
