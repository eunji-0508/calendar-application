package org.example.calendarapplication.Auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.Auth.dto.AuthLoginRequestDto;
import org.example.calendarapplication.Auth.dto.AuthLoginResponseDto;
import org.example.calendarapplication.Auth.dto.AuthSignUpRequestDto;
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
            @RequestBody AuthSignUpRequestDto authRequestDto
    ) {
        authService.signup(authRequestDto);

        return "회원 가입이 완료되었습니다.";
    }

    // 로그인
    @PostMapping("/login")
    public String login(
            @Valid @RequestBody AuthLoginRequestDto authLoginRequestDto,
            HttpServletRequest httpServletRequest
    ) {
        // Cookie Session을 발급함
        AuthLoginResponseDto result = authService.login(authLoginRequestDto);

        HttpSession session = httpServletRequest.getSession();  // 신규 세션을 생성함. JSESSIONID 쿠키를 발급함
        session.setAttribute("LOGIN_USER", result.getId());  // 서버 메모리에 세션을 저장함

        return "해당 User로 로그인이 완료되었습니다.";
    }

    // 로그아웃
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        // 로그인하지 않았을 경우 HttpSession이 null로 반환하도록 false로 지정함
        HttpSession session = request.getSession(false);

        // 세션이 존재할 경우(로그인 했을 경우)
        if (session != null) {
            session.invalidate();   // 해당 세션(데이터)을 삭제함
        }
    }
}
