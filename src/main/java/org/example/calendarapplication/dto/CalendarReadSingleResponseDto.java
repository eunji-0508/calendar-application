package org.example.calendarapplication.dto;

import lombok.Getter;

import java.time.LocalDateTime;

// 일정 단건 조회용 ResponseDto
// ResponseDto에는 final 키워드, 생성자 넣어야함
@Getter
public class CalendarReadSingleResponseDto {
    private final Long id;
    private final String username;    // 작성 유저명
    private final String title;       // 할일 제목
    private final String content;     // 할일 내용
    private final LocalDateTime createdAt;  // 작성일
    private final LocalDateTime modifiedAt; // 수정일

    // 생성자
    public CalendarReadSingleResponseDto(Long id, String username, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
