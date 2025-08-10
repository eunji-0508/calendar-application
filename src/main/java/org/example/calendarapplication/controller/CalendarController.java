package org.example.calendarapplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.dto.CalendarCreateRequestDto;
import org.example.calendarapplication.dto.CalendarCreateResponseDto;
import org.example.calendarapplication.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    // 일정 생성
    @PostMapping("/calendars")
    public ResponseEntity<CalendarCreateResponseDto> createCalendar(
            @RequestBody CalendarCreateRequestDto calendarCreateRequestDto
            ) {
        return ResponseEntity.ok(calendarService.createCalendar(calendarCreateRequestDto));
    }

}
