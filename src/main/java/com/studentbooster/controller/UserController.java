package com.studentbooster.controller;

import com.studentbooster.dto.UserDto;
import com.studentbooster.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        log.info("Fetching user with id: {}", userId);
        UserDto user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        log.info("Fetching user with username: {}", username);
        UserDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto updateRequest) {
        log.info("Updating user with id: {}", userId);
        UserDto updatedUser = userService.updateUserProfile(userId, updateRequest);
        return ResponseEntity.ok(updatedUser);
    }
}