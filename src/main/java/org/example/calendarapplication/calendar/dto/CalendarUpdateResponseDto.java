package org.example.calendarapplication.calendar.dto;

import lombok.Getter;
import java.time.LocalDateTime;

// 일정 수정용 ResponseDto
// ResponseDto에는 final 키워드, 생성자 넣어야함
@Getter
public class CalendarUpdateResponseDto {
    private final Long id;              // 일정 ID
    private final String title;       // 할일 제목
    private final String content;     // 할일 내용
    private final LocalDateTime createdAt;  // 작성일
    private final LocalDateTime modifiedAt; // 수정일

    // 생성자
    public CalendarUpdateResponseDto(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
