package com.younggal.project.exception.handler;

import com.younggal.project.dto.ErrorResponseDto;
import com.younggal.project.exception.AttachmentNotFoundException;
import com.younggal.project.exception.CategoryBannerNotFoundException;
import com.younggal.project.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    /**
     * Category가 존재하지 않는 에러 (BAD_REQUEST)
     */
    @ExceptionHandler(CategoryNotFoundException.class)
    protected ResponseEntity<ErrorResponseDto> handleCategoryNotFoundException(Exception e) {
        log.error("CategoryNotFoundException", e);
        return ErrorResponseDto.toResponseEntity(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * Category Banner가 존재하지 않는 에러 (BAD_REQUEST)
     */
    @ExceptionHandler(CategoryBannerNotFoundException.class)
    protected ResponseEntity<ErrorResponseDto> handleCategoryBannerNotFoundException(Exception e) {
        log.error("CategoryBannerNotFoundException", e);
        return ErrorResponseDto.toResponseEntity(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * 첨부파일이 존재하지 않는 에러 (BAD_REQUEST)
     */
    @ExceptionHandler(AttachmentNotFoundException.class)
    protected ResponseEntity<ErrorResponseDto> handleAttachmentNotFoundException(Exception e) {
        log.error("AttachmentNotFoundException", e);
        return ErrorResponseDto.toResponseEntity(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    /**
     * Exception 나머지 에러 일 경우
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        log.error("Exception", e);
        return ErrorResponseDto.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}
