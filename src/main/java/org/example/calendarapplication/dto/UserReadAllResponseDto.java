package org.example.calendarapplication.dto;

import lombok.Getter;

import java.time.LocalDateTime;

// 전체 유저 조회용 ResponseDto
// ResponseDto에는 final 키워드, 생성자 넣어야함
@Getter
public class UserReadAllResponseDto {
    private final Long id;            // 유저 ID
    private final String username;    // 유저명
    private final String email;       // 이메일
    private final LocalDateTime createdAt;  // 작성일
    private final LocalDateTime modifiedAt; // 수정일

    // 생성자
    public UserReadAllResponseDto(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
