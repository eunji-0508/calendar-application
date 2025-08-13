package org.example.calendarapplication.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

// 유저 수정용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class UserUpdateRequestDto {
    // 공백 여부와 최대 글자수를 검증함
    @NotBlank(message = "유저명을 입력해주시길 바랍니다.")
    @Size(max = 4, message = "유저명은 4글자 이내로 입력해주시길 바랍니다.")
    private String username;    // 유저명

    // 이메일 형식과 공백 여부를 검증함
    @Email @NotBlank(message = "이메일을 입력해주시길 바랍니다.")
    private String email;       // 이메일
}
