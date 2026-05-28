package com.studentbooster.service;

import com.studentbooster.dto.LessonDto;
import com.studentbooster.entity.Lesson;
import com.studentbooster.exception.ResourceNotFoundException;
import com.studentbooster.repository.LessonRepository;
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
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonDto createLesson(Lesson lesson) {
        log.info("Creating new lesson: {}", lesson.getTitle());
        Lesson savedLesson = lessonRepository.save(lesson);
        return LessonDto.fromEntity(savedLesson);
    }

    @Transactional(readOnly = true)
    public LessonDto getLessonById(Long lessonId) {
        log.info("Fetching lesson with id: {}", lessonId);
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));
        return LessonDto.fromEntity(lesson);
    }

    @Transactional(readOnly = true)
    public List<LessonDto> getLessonsByUserId(Long userId) {
        log.info("Fetching lessons for user: {}", userId);
        return lessonRepository.findByUserId(userId).stream()
                .map(LessonDto::fromEntity)
                .collect(Collectors.toList());
    }

    public LessonDto updateLesson(Long lessonId, Lesson lessonUpdate) {
        log.info("Updating lesson: {}", lessonId);
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));
        lesson.setTitle(lessonUpdate.getTitle());
        lesson.setDescription(lessonUpdate.getDescription());
        Lesson updatedLesson = lessonRepository.save(lesson);
        return LessonDto.fromEntity(updatedLesson);
    }

    public void deleteLesson(Long lessonId) {
        log.info("Deleting lesson: {}", lessonId);
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));
        lessonRepository.delete(lesson);
    }
}