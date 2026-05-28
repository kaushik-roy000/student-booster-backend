package com.studentbooster.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<HealthStatus> health() {
        return ResponseEntity.ok(new HealthStatus(
                "UP",
                "Student Booster Backend is running",
                LocalDateTime.now()
        ));
    }

    @Data
    @AllArgsConstructor
    private static class HealthStatus {
        private String status;
        private String message;
        private LocalDateTime timestamp;
    }
}
