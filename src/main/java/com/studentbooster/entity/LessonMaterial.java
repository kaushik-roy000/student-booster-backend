package com.studentbooster.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "lesson_materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long lessonId;

    @Column(nullable = false, length = 255)
    private String fileName;

    @Column(nullable = false, length = 50)
    private String fileType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String filePath;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime uploadedAt;
}