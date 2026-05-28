package com.studentbooster.dto;

import com.studentbooster.entity.GeneratedContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneratedContentDto {
    private Long id;
    private Long lessonMaterialId;
    private String contentType;
    private String content;
    private LocalDateTime createdAt;

    public static GeneratedContentDto fromEntity(GeneratedContent entity) {
        return GeneratedContentDto.builder()
                .id(entity.getId())
                .lessonMaterialId(entity.getLessonMaterialId())
                .contentType(entity.getContentType())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
