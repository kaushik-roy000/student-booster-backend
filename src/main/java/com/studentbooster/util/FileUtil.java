package com.studentbooster.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@Slf4j
public class FileUtil {

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    public String getUploadDirectory() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
            log.info("Upload directory ready at: {}", uploadDir);
            return uploadDir;
        } catch (Exception e) {
            log.error("Error creating upload directory: {}", e.getMessage());
            return "uploads";
        }
    }

    public String sanitizeFilename(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
