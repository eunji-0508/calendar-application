package org.example.calendarapplication.dto;

import lombok.Getter;

// 전체 일정 조회용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class CalendarReadAllRequestDto {
    private String title;       // 할일 제목
    private String content;     // 할일 내용
}
