package org.example.calendarapplication.calendar.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

// 일정 생성용 RequestDto
// RequestDto에는 final 키워드, 생성자 넣지 않음
@Getter
public class CalendarCreateRequestDto {
    @Size(max = 10, message = "할일 제목은 10글자 이내여야 합니다.")
    private String title;       // 할일 제목

    private String content;     // 할일 내용
}
