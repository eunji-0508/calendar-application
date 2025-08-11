package org.example.calendarapplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.dto.UserCreateRequestDto;
import org.example.calendarapplication.dto.UserCreateResponseDto;
import org.example.calendarapplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
