package org.example.calendarapplication.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.user.dto.*;
import org.example.calendarapplication.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 유저 생성 (Create)
    @PostMapping("/users")
    public ResponseEntity<UserCreateResponseDto> createUser(
            @RequestBody UserCreateRequestDto userCreateRequestDto
            ) {
        return ResponseEntity.ok(userService.createUser(userCreateRequestDto));
    }

    // 전체 유저 조회 (Read)
    @GetMapping("/users")
    public ResponseEntity<List<UserReadAllResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 유저 단건 조회 (Read)
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserReadSingleResponseDto> getUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    // 유저 수정 (Update)
    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserUpdateResponseDto> updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequestDto userUpdateRequestDto
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, userUpdateRequestDto));
    }

    // 유저 삭제 (Delete)
    @DeleteMapping("/users/{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
    }
}
