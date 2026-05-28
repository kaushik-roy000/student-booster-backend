package com.studentbooster.service;

import com.studentbooster.dto.LessonMaterialDto;
import com.studentbooster.entity.LessonMaterial;
import com.studentbooster.exception.ResourceNotFoundException;
import com.studentbooster.repository.LessonMaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LessonMaterialService {

    private final LessonMaterialRepository lessonMaterialRepository;

    public LessonMaterialDto uploadMaterial(LessonMaterial material) {
        log.info("Uploading material: {} for lesson: {}", material.getFileName(), material.getLessonId());
        LessonMaterial savedMaterial = lessonMaterialRepository.save(material);
        return LessonMaterialDto.fromEntity(savedMaterial);
    }

    @Transactional(readOnly = true)
    public LessonMaterialDto getMaterialById(Long materialId) {
        log.info("Fetching material with id: {}", materialId);
        LessonMaterial material = lessonMaterialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found with id: " + materialId));
        return LessonMaterialDto.fromEntity(material);
    }

    @Transactional(readOnly = true)
    public List<LessonMaterialDto> getMaterialsByLessonId(Long lessonId) {
        log.info("Fetching materials for lesson: {}", lessonId);
        return lessonMaterialRepository.findByLessonId(lessonId).stream()
                .map(LessonMaterialDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteMaterial(Long materialId) {
        log.info("Deleting material: {}", materialId);
        LessonMaterial material = lessonMaterialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found with id: " + materialId));
        lessonMaterialRepository.delete(material);
    }
}