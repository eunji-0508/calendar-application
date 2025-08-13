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

@RestController             // Bean으로 등록할 때 쓰는 annotation, @RequestBody + @Controller (Restful API를 작성하기 위해서 사용함)
@RequiredArgsConstructor       // 필수(final) 필드만을 매개변수로 하는 생성자를 자동으로 생성함
public class AuthController {
    // 스프링 컨테이너로부터 의존성을 주입받는 싱글톤 AuthService 객체
    // 싱글톤: 어떤 클래스를 하나의 객체로 만드는 것
    private final AuthService authService;

    /*
    기능: 회원 가입
    HTTP Method: POST
    URL: /signup (http://localhost:8080/signup)
    인증/인가 : 없음
    Request Body: AuthSignUpRequestDto
    JSON 예시: {"username" : "홍길동", "email" : "hong@gmail.com", "password" : "1111"}
    Response: 성공 시 문자열 "회원 가입이 완료되었습니다."을 출력함
    */
    @PostMapping("/signup")
    public String signup(
            // HTTP 요청 Body에 담긴 JSON 데이터를 AuthSignUpRequestDto 객체로 변환함
            // 필드에 선언된 유효성 검증(@NotBlank, @Email 등)을 수행함
            @Valid @RequestBody AuthSignUpRequestDto authSignUpRequestDto
    ) {
        // DTO를 활용하여 회원 가입 로직을 수행함
        authService.signup(authSignUpRequestDto);

        return "회원 가입이 완료되었습니다.";
    }

    /*
    기능: 로그인
    HTTP Method: POST
    URL: /login (http://localhost:8080/login)
    인증/인가 : 없음
    Request Body: AuthLoginRequestDto
    JSON 예시: {"email" : "hong@gmail.com", "password" : "1111"}
    Response: 성공 시 문자열 "해당 User로 로그인이 완료되었습니다."을 출력함
    */
    @PostMapping("/login")
    public String login(
            // HTTP 요청 Body에 담긴 JSON 데이터를 AuthLoginRequestDto 객체로 변환함
            // 필드에 선언된 유효성 검증(@NotBlank, @Email 등)을 수행함
            @Valid @RequestBody AuthLoginRequestDto authLoginRequestDto,
            HttpServletRequest httpServletRequest                   // 각 HTTP 요청에서 주고받는 값들을 담고 있음
    ) {
        // Cookie Session을 발급함
        AuthLoginResponseDto result = authService.login(authLoginRequestDto);

        HttpSession session = httpServletRequest.getSession();  // 신규 세션을 생성함. JSESSIONID 쿠키를 발급함
        session.setAttribute("LOGIN_USER", result.getId());  // 서버 메모리에 세션을 저장함. 키: LOGIN_USER, 값:result.getId()

        return "해당 User로 로그인이 완료되었습니다.";
    }

    /*
    기능: 로그아웃
    HTTP Method: POST
    URL: /logout (http://localhost:8080/logout)
    인증/인가 : 로그인 필요
    */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {             // 각 HTTP 요청에서 주고받는 값들을 담고 있음
        // 로그인하지 않았을 경우 HttpSession이 null로 반환하도록 false로 지정함
        HttpSession session = request.getSession(false);

        // 세션이 존재할 경우(로그인 했을 경우)
        if (session != null) {
            session.invalidate();   // 해당 세션(데이터)을 삭제함
        }
    }
}
