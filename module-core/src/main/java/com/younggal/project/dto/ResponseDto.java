package com.younggal.project.dto;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class ResponseDto<T> {
    private int code;
    private String message;
    private T response;

    public ResponseDto(T response) {
        this.code = 200;
        this.message = "ok";
        this.response = response;
    }

    public static <T> ResponseEntity<ResponseDto<T>> setResponse(T data) {
        ResponseDto<T> responseDto = new ResponseDto<>(data);

        return ResponseEntity.ok()
                .body(responseDto);
    }

    public static ResponseEntity<ResponseDto<String>> setResponse() {
        ResponseDto<String> responseDto = new ResponseDto<>("ok");
        return ResponseEntity.ok()
                .body(responseDto);
    }
}
