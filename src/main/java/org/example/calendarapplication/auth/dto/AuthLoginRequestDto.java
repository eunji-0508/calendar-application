package org.example.calendarapplication.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

// 로그인용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class AuthLoginRequestDto {
    // 이메일 형식과 공백 여부를 검증함
    @Email @NotBlank(message = "이메일을 입력해주시길 바랍니다.")
    private String email;       // 이메일

    // 공백 여부와 최소 글자수를 검증함
    @NotBlank(message = "비밀번호를 입력해주시길 바랍니다.")
    @Size(min = 8, message = "비밀번호는 8글자 이상이어야 합니다.")
    private String password;    // 비밀번호
}
