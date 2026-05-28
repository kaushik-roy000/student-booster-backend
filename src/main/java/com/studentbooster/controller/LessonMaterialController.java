package com.studentbooster.controller;

import com.studentbooster.dto.LessonMaterialDto;
import com.studentbooster.entity.LessonMaterial;
import com.studentbooster.service.LessonMaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
@Slf4j
public class LessonMaterialController {

    private final LessonMaterialService lessonMaterialService;

    @PostMapping
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<LessonMaterialDto> uploadMaterial(@RequestBody LessonMaterial material) {
        log.info("Uploading material: {}", material.getFileName());
        LessonMaterialDto uploadedMaterial = lessonMaterialService.uploadMaterial(material);
        return ResponseEntity.status(HttpStatus.CREATED).body(uploadedMaterial);
    }

    @GetMapping("/{materialId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<LessonMaterialDto> getMaterial(@PathVariable Long materialId) {
        log.info("Fetching material with id: {}", materialId);
        LessonMaterialDto material = lessonMaterialService.getMaterialById(materialId);
        return ResponseEntity.ok(material);
    }

    @GetMapping("/lesson/{lessonId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<List<LessonMaterialDto>> getLessonMaterials(@PathVariable Long lessonId) {
        log.info("Fetching materials for lesson: {}", lessonId);
        List<LessonMaterialDto> materials = lessonMaterialService.getMaterialsByLessonId(lessonId);
        return ResponseEntity.ok(materials);
    }

    @DeleteMapping("/{materialId}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long materialId) {
        log.info("Deleting material with id: {}", materialId);
        lessonMaterialService.deleteMaterial(materialId);
        return ResponseEntity.noContent().build();
    }
}