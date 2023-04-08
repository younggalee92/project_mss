package com.younggal.project.common.api;

import com.younggal.project.common.service.AttachmentService;
import com.younggal.project.domain.common.Attachment;
import com.younggal.project.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;
    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadAttachment(@RequestParam("file") MultipartFile file) throws IOException {
        Attachment attachment = attachmentService.uploadAttachment(file);
        return ResponseDto.setResponse(attachment);
    }
}
