package org.example.calendarapplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.dto.CalendarCreateRequestDto;
import org.example.calendarapplication.dto.CalendarCreateResponseDto;
import org.example.calendarapplication.dto.CalendarReadAllResponseDto;
import org.example.calendarapplication.dto.CalendarReadSingleResponseDto;
import org.example.calendarapplication.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    // 일정 생성 (Create)
    @PostMapping("/calendars")
    public ResponseEntity<CalendarCreateResponseDto> createCalendar(
            @RequestBody CalendarCreateRequestDto calendarCreateRequestDto
            ) {
        return ResponseEntity.ok(calendarService.createCalendar(calendarCreateRequestDto));
    }

    // 전체 일정 조회 (Read)
    @GetMapping("/calendars")
    public ResponseEntity<List<CalendarReadAllResponseDto>> getAllCalendars() {
        return ResponseEntity.ok(calendarService.getAllCalendars());
    }

    // 일정 단건 조회 (Read)
    @GetMapping("/calendars/{calendarId}")
    public ResponseEntity<CalendarReadSingleResponseDto> getCalendar(
            @PathVariable Long calendarId
    ) {
        return ResponseEntity.ok(calendarService.getCalendar(calendarId));
    }

}
