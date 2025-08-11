package org.example.calendarapplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.dto.*;
import org.example.calendarapplication.service.CalendarService;
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
    @GetMapping("/calendars/{calendarId}")
    public ResponseEntity<CalendarReadSingleResponseDto> getCalendar(
            @PathVariable Long calendarId
    ) {
        return ResponseEntity.ok(calendarService.getCalendar(calendarId));
    }

    // 일정 수정 (Update)
    @PatchMapping("/calendars/{calendarId}")
    public ResponseEntity<CalendarUpdateResponseDto> updateCalendar(
            @PathVariable Long calendarId,
            @RequestBody CalendarUpdateRequestDto calendarUpdateRequestDto
    ) {
        return ResponseEntity.ok(calendarService.updateCalendar(calendarId, calendarUpdateRequestDto));
    }

    // 일정 삭제 (Delete)
    @DeleteMapping("/calendars/{calendarId}")
    public void deleteCalendar(
            @PathVariable Long calendarId
    ) {
        calendarService.deleteCalendar(calendarId);
    }

}
