package com.studentbooster.dto;

import com.studentbooster.entity.LessonMaterial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonMaterialDto {
    private Long id;
    private Long lessonId;
    private String fileName;
    private String fileType;
    private String filePath;
    private LocalDateTime uploadedAt;

    public static LessonMaterialDto fromEntity(LessonMaterial material) {
        return LessonMaterialDto.builder()
                .id(material.getId())
                .lessonId(material.getLessonId())
                .fileName(material.getFileName())
                .fileType(material.getFileType())
                .filePath(material.getFilePath())
                .uploadedAt(material.getUploadedAt())
                .build();
    }
}