package com.younggal.project.common.api;

import com.younggal.project.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping("/check")
    public ResponseEntity<?> checkHealth() {
        return ResponseDto.setResponse();
    }
}
