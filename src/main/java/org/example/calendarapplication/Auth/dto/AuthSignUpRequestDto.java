package org.example.calendarapplication.Auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

// 회원 가입용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class AuthSignUpRequestDto {
    // 공백 여부를 검증함
    @NotBlank(message = "유저명을 입력해주시길 바랍니다.")
    private String username;    // 유저명

    // 이메일 형식과 공백 여부를 검증함
    @Email @NotBlank(message = "이메일을 입력해주시길 바랍니다.")
    private String email;       // 이메일

    // 공백 여부를 검증함
    @NotBlank(message = "비밀번호를 입력해주시길 바랍니다.")
    private String password;    // 비밀번호
}
