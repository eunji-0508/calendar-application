package org.example.calendarapplication.Auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthLoginRequestDto {
    // 인덱스 0
    @Email @NotBlank(message = "이메일을 입력해주시길 바랍니다.")
    private String email;

    @NotBlank(message = "비밀번호를  입력해주시길 바랍니다.")
    private String password;
}
