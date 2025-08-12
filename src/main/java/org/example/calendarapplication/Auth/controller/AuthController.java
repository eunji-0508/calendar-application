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

    // 로그인
    @PostMapping("/login")
    public String login(
            @RequestBody AuthRequestDto authRequestDto,
            HttpServletRequest httpServletRequest
    ) {
        // Cookie Session을 발급함
        AuthResponseDto result = authService.login(authRequestDto);

        HttpSession session = httpServletRequest.getSession();  // 신규 세션을 생성함. JSESSIONID 쿠키를 발급함
        session.setAttribute("USER_LOGIN", result.getId());  // 서버 메모리에 세션을 저장함

        return "해당 User로 로그인이 완료되었습니다.";
    }
}
