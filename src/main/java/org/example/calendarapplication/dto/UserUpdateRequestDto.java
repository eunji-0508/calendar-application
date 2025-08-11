package org.example.calendarapplication.dto;

import lombok.Getter;

// 유저 수정용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class UserUpdateRequestDto {
    private String username;    // 유저명
    private String email;       // 이메일
}
