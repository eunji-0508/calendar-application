package org.example.calendarapplication.dto;

import lombok.Getter;

// 일정 수정용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class CalendarUpdateRequestDto {
    private String title;       // 할일 제목
    private String content;     // 할일 내용
}
