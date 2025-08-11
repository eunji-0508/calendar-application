package org.example.calendarapplication.dto;

import lombok.Getter;

// 전체 유저 조회용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class UserReadAllRequestDto {
    private String username;    // 유저명
    private String email;       // 이메일
}
