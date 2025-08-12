package org.example.calendarapplication.Auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.Auth.dto.AuthRequestDto;
import org.example.calendarapplication.Auth.dto.AuthResponseDto;
import org.example.calendarapplication.Auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // 회원 가입
    @PostMapping("/signup")
    public String signup(
            @RequestBody AuthRequestDto authRequestDto
            ) {
        authService.signup(authRequestDto);

        return "회원 가입이 완료되었습니다.";
    }
}
