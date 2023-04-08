package com.younggal.project.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponseDto {
    private final int code;
    private final String message;
    private final String hint;

    public static ResponseEntity<ErrorResponseDto> toResponseEntity(int status, String message) {
        return ResponseEntity.status(status)
                .body(ErrorResponseDto.builder()
                        .code(status)
                        .message(message)
                        .build());
    }

    public static ResponseEntity<ErrorResponseDto> toResponseEntity(int status, String message, String hint) {
        return ResponseEntity.status(status)
                .body(ErrorResponseDto.builder()
                        .code(status)
                        .message(message)
                        .hint(hint)
                        .build());
    }
}
