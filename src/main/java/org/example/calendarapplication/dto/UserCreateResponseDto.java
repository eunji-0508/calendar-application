package org.example.calendarapplication.dto;

import lombok.Getter;

// 유저 생성용 ResponseDto
// ResponseDto에는 final 키워드, 생성자 넣어야함
@Getter
public class UserCreateResponseDto {
    private final Long id;
    private final String username;    // 유저명
    private final String email;       // 이메일

    // 생성자
    public UserCreateResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
