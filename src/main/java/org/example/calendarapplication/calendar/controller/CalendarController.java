package org.example.calendarapplication.calendar.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.calendar.dto.*;
import org.example.calendarapplication.calendar.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    // 일정 생성 (Create)
    @PostMapping("users/{userId}/calendars")
    public ResponseEntity<CalendarCreateResponseDto> createCalendar(
            @PathVariable Long userId,
            @RequestBody CalendarCreateRequestDto calendarCreateRequestDto
            ) {
        return ResponseEntity.ok(calendarService.createCalendar(userId, calendarCreateRequestDto));
    }

    // 전체 일정 조회 (Read)
    @GetMapping("users/{userId}/calendars")
    public ResponseEntity<List<CalendarReadAllResponseDto>> getAllCalendars(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(calendarService.getAllCalendars(userId));
    }

    // 일정 단건 조회 (Read)
    @GetMapping("users/{userId}/calendars/{calendarId}")
    public ResponseEntity<CalendarReadSingleResponseDto> getCalendar(
            @PathVariable Long userId,
            @PathVariable Long calendarId
    ) {
        return ResponseEntity.ok(calendarService.getCalendar(userId, calendarId));
    }

    // 일정 수정 (Update)
    @PatchMapping("users/{userId}/calendars/{calendarId}")
    public ResponseEntity<CalendarUpdateResponseDto> updateCalendar(
            @PathVariable Long userId,
            @PathVariable Long calendarId,
            @RequestBody CalendarUpdateRequestDto calendarUpdateRequestDto
    ) {
        return ResponseEntity.ok(calendarService.updateCalendar(userId, calendarId, calendarUpdateRequestDto));
    }

    // 일정 삭제 (Delete)
    @DeleteMapping("users/{userId}/calendars/{calendarId}")
    public void deleteCalendar(
            @PathVariable Long userId,
            @PathVariable Long calendarId
    ) {
        calendarService.deleteCalendar(userId, calendarId);
    }
}
