package org.example.calendarapplication.Auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthLoginRequestDto {
    // 인덱스 0
    @Email @NotBlank(message = "올바른 이메일 형태가 아닙니다.")
    private String email;
    private String password;
}
