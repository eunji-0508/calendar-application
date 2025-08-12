package org.example.calendarapplication.user.dto;

import lombok.Getter;

// 유저 단건 조회용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class UserReadSingleRequestDto {
    private String username;    // 유저명
    private String email;       // 이메일
}
