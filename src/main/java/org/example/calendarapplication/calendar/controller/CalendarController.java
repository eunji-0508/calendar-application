package org.example.calendarapplication.calendar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.calendar.dto.*;
import org.example.calendarapplication.calendar.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController             // Bean으로 등록할 때 쓰는 annotation, @ResponseBody + @Controller (Restful API를 작성하기 위해서 사용함)
@RequiredArgsConstructor       // 필수(final) 필드만을 매개변수로 하는 생성자를 자동으로 생성함
public class CalendarController {
    // 스프링 컨테이너로부터 의존성을 주입받는 싱글톤 CalendarService 객체
    // 싱글톤: 어떤 클래스를 하나의 객체로 만드는 것
    private final CalendarService calendarService;

    /*
    기능: 일정 생성 (Create)
    HTTP Method: POST
    URL: /users/{userId}/calendars (http://localhost:8080/users/{userId}/calendars)
    인증/인가 : 로그인 필요
    Request Body: CalendarCreateRequestDto
    JSON 예시: {"title" : "의적 활동", "content" : "서쪽가서 의적 활동하기"}
    Response: 성공 시 생성된 일정 정보를 JSON 형태로 반환함
    */
    @PostMapping("/users/{userId}/calendars")
    public ResponseEntity<CalendarCreateResponseDto> createCalendar(
            @PathVariable Long userId,                                          // @PathVariable: URL로 전달된 값을 파라미터로 받아오는 역할을 함
            @Valid @RequestBody CalendarCreateRequestDto calendarCreateRequestDto      // HTTP 요청 Body에 담긴 JSON 데이터를 CalendarCreateRequestDto 객체로 변환함
    ) {
        // 새로운 일정을 생성하고, 생성된 일정 정보를 HTTP 200 OK와 함께 반환함
        return ResponseEntity.ok(calendarService.createCalendar(userId, calendarCreateRequestDto));
    }

    /*
    기능: 전체 일정 조회 (Read)
    HTTP Method: GET
    URL: /users/{userId}/calendars (http://localhost:8080/users/{userId}/calendars)
    인증/인가 : 로그인 필요
    Response: 성공 시 해당 유저의 모든 일정 정보를 JSON 형태 리스트로 반환함
    */
    @GetMapping("/users/{userId}/calendars")
    public ResponseEntity<List<CalendarReadAllResponseDto>> getAllCalendars(
            // @PathVariable: URL로 전달된 값을 파라미터로 받아오는 역할을 함
            @PathVariable Long userId
    ) {
        // 해당 유저의 모든 일정 정보를 가져와 HTTP 200 OK와 함께 반환함
        return ResponseEntity.ok(calendarService.getAllCalendars(userId));
    }

    /*
    기능: 일정 단건 조회 (Read)
    HTTP Method: GET
    URL: /users/{userId}/calendars/{calendarId} (http://localhost:8080/users/{userId}/calendars/{calendarId})
    인증/인가 : 로그인 필요
    Response: 성공 시 해당 유저의 특정 일정 정보를 JSON 형태로 반환함
    */
    @GetMapping("/users/{userId}/calendars/{calendarId}")
    public ResponseEntity<CalendarReadSingleResponseDto> getCalendar(
            // @PathVariable: URL로 전달된 값을 파라미터로 받아오는 역할을 함
            @PathVariable Long userId,
            @PathVariable Long calendarId
    ) {
        // 해당 유저의 특정 일정 정보를 가져와 HTTP 200 OK와 함께 반환함
        return ResponseEntity.ok(calendarService.getCalendar(userId, calendarId));
    }

    /*
    기능: 일정 수정 (Update)
    HTTP Method: PATCH
    URL: /users/{userId}/calendars/{calendarId} (http://localhost:8080/users/{userId}/calendars/{calendarId})
    인증/인가 : 로그인 필요
    Response: 성공 시 해당 유저의 수정된 특정 일정 정보를 JSON 형태로 반환함
    */
    @PatchMapping("/users/{userId}/calendars/{calendarId}")
    public ResponseEntity<CalendarUpdateResponseDto> updateCalendar(
            // @PathVariable: URL로 전달된 값을 파라미터로 받아오는 역할을 함
            @PathVariable Long userId,
            @PathVariable Long calendarId,
            @Valid @RequestBody CalendarUpdateRequestDto calendarUpdateRequestDto  // HTTP 요청 Body에 담긴 JSON 데이터를 CalendarUpdateRequestDto 객체로 변환함
    ) {
        // 해당 유저의 특정 일정 정보를 수정하고, 수정된 일정 정보를 HTTP 200 OK와 함께 반환함
        return ResponseEntity.ok(calendarService.updateCalendar(userId, calendarId, calendarUpdateRequestDto));
    }

    /*
    기능: 일정 삭제 (Delete)
    HTTP Method: DELETE
    URL: /users/{userId}/calendars/{calendarId} (http://localhost:8080/users/{userId}/calendars/{calendarId})
    인증/인가 : 로그인 필요
    Response: 삭제 성공 시 HTTP 200 OK를 반환하고, Body는 없음
    */
    @DeleteMapping("/users/{userId}/calendars/{calendarId}")
    public void deleteCalendar(
            // @PathVariable: URL로 전달된 값을 파라미터로 받아오는 역할을 함
            @PathVariable Long userId,
            @PathVariable Long calendarId
    ) {
        // 해당 유저의 특정 일정을 삭제함
        calendarService.deleteCalendar(userId, calendarId);
    }
}
