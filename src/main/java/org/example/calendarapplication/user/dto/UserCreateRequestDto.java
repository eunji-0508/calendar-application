package org.example.calendarapplication.user.dto;

import lombok.Getter;

// 유저 생성용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class UserCreateRequestDto {
    private String username;    // 유저명
    private String email;       // 이메일
    private String password;    // 비밀번호
}
