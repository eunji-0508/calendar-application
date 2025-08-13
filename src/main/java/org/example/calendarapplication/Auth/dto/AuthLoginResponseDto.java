package org.example.calendarapplication.Auth.dto;

import lombok.Getter;

// 로그인용 ResponseDto
// ResponseDto에는 final 키워드, 생성자 넣어야함
@Getter
public class AuthLoginResponseDto {
    private final Long id;      // 유저 ID

    // 생성자
    public AuthLoginResponseDto(Long id) {
        this.id = id;
    }
}
