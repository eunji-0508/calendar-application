package org.example.calendarapplication.Auth.dto;

import lombok.Getter;

@Getter
public class AuthSignUpRequestDto {
    private String username;    // 유저명
    private String email;       // 이메일
    private String password;    // 비밀번호
}
