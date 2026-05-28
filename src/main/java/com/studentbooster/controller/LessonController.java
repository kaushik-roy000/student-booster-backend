package com.studentbooster.controller;

import com.studentbooster.dto.LessonDto;
import com.studentbooster.entity.Lesson;
import com.studentbooster.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
@Slf4j
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<LessonDto> createLesson(@RequestBody Lesson lesson) {
        log.info("Creating new lesson: {}", lesson.getTitle());
        LessonDto createdLesson = lessonService.createLesson(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);
    }

    @GetMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<LessonDto> getLesson(@PathVariable Long lessonId) {
        log.info("Fetching lesson with id: {}", lessonId);
        LessonDto lesson = lessonService.getLessonById(lessonId);
        return ResponseEntity.ok(lesson);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<List<LessonDto>> getUserLessons(@PathVariable Long userId) {
        log.info("Fetching lessons for user: {}", userId);
        List<LessonDto> lessons = lessonService.getLessonsByUserId(userId);
        return ResponseEntity.ok(lessons);
    }

    @PutMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<LessonDto> updateLesson(@PathVariable Long lessonId, @RequestBody Lesson lessonUpdate) {
        log.info("Updating lesson with id: {}", lessonId);
        LessonDto updatedLesson = lessonService.updateLesson(lessonId, lessonUpdate);
        return ResponseEntity.ok(updatedLesson);
    }

    @DeleteMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long lessonId) {
        log.info("Deleting lesson with id: {}", lessonId);
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();
    }
}