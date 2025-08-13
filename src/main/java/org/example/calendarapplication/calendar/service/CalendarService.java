package org.example.calendarapplication.calendar.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.calendar.dto.*;
import org.example.calendarapplication.calendar.entity.Calendar;
import org.example.calendarapplication.calendar.repository.CalendarRepository;
import org.example.calendarapplication.user.entity.User;
import org.example.calendarapplication.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service                        // Bean으로 등록할 때 쓰는 annotation
@RequiredArgsConstructor        // 필수(final) 필드만을 매개변수로 하는 생성자를 자동으로 생성함
public class CalendarService {
    // 스프링 컨테이너로부터 의존성을 주입받는 싱글톤 CalendarRepository, UserRepository 객체
    // 싱글톤: 어떤 클래스를 하나의 객체로 만드는 것
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    // 일정 생성 (Create)
    @Transactional
    public CalendarCreateResponseDto createCalendar(Long userId, CalendarCreateRequestDto calendarCreateRequestDto) {
        // 유저 ID가 일치하는 User 엔티티를 조회함
        // 일치하는 User 엔티티가 없으면 예외를 발생시킴
        User user = findUserById(userId);

        // DTO로부터 전달받은 데이터를 통해 Calendar 엔티티를 생성함
        Calendar calendar = new Calendar(
                calendarCreateRequestDto.getTitle(),
                calendarCreateRequestDto.getContent(),
                user
        );

        // save 메서드를 활용하여 Calendar 엔티티를 데이터베이스에 저장하고, 저장된 객체를 반환함
        Calendar savedCalendar = calendarRepository.save(calendar);

        // savedCalendar의 정보를 이용하여 응답 DTO을 생성하고 반환함
        return new CalendarCreateResponseDto(
                savedCalendar.getId(),
                savedCalendar.getTitle(),
                savedCalendar.getContent(),
                savedCalendar.getCreatedAt(),
                savedCalendar.getModifiedAt()
        );
    }

    // 전체 일정 조회 (Read)
    @Transactional(readOnly = true)     // 읽기 전용
    public List<CalendarReadAllResponseDto> getAllCalendars(Long userId) {
        // 유저 ID가 일치하는 User 엔티티를 조회함
        // 일치하는 User 엔티티가 없으면 예외를 발생시킴
        User user = findUserById(userId);

        // findAllByUser 메서드를 활용하여 해당 유저의 모든 일정 정보를 리스트로 가져옴
        List<Calendar> calendars = calendarRepository.findAllByUser(user);

        // 응답 DTO로 변환하여 리스트로 반환함
        return calendars.stream().map(calendar -> new CalendarReadAllResponseDto(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContent(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        )).collect(Collectors.toList());
    }

    // 일정 단건 조회 (Read)
    @Transactional(readOnly = true)     // 읽기 전용
    public CalendarReadSingleResponseDto getCalendar(Long userId, Long calendarId) {
        // 유저 ID와 캘린더 ID가 일치하는 Calendar 엔티티를 조회함
        // 일치하는 Calendar 엔티티가 없으면 예외를 발생시킴
        Calendar calendar = findCalendarByUserIdAndCalendarId(userId, calendarId);

        // calendar의 정보를 이용하여 응답 DTO을 생성하고 반환함
        return new CalendarReadSingleResponseDto(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContent(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }

    // 일정 수정 (Update)
    @Transactional
    public CalendarUpdateResponseDto updateCalendar(Long userId, Long calendarId, CalendarUpdateRequestDto calendarUpdateRequestDto) {
        // 유저 ID와 캘린더 ID가 일치하는 Calendar 엔티티를 조회함
        // 일치하는 Calendar 엔티티가 없으면 예외를 발생시킴
        Calendar calendar = findCalendarByUserIdAndCalendarId(userId, calendarId);

        // Calendar 엔티티의 제목(title)과 내용(content)을 요청 DTO의 값으로 수정함
        calendar.update(calendarUpdateRequestDto.getTitle(), calendarUpdateRequestDto.getContent());

        // calendar의 정보를 이용하여 응답 DTO을 생성하고 반환함
        return new CalendarUpdateResponseDto(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContent(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }

    // 일정 삭제 (Delete)
    @Transactional
    public void deleteCalendar(Long userId, Long calendarId) {
        // 유저 ID와 캘린더 ID가 일치하는 Calendar 엔티티를 조회함
        // 일치하는 Calendar 엔티티가 없으면 예외를 발생시킴
        findCalendarByUserIdAndCalendarId(userId, calendarId);

        // calendarId에 해당하는 Calendar 엔티티를 데이터베이스에서 삭제함
        calendarRepository.deleteById(calendarId);
    }

    // 주어진 userId로 User 엔티티를 조회하는 메서드
    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID는 존재하지 않습니다.")
        );
    }

    // 주어진 userId와 calendarId에 해당하는 Calendar 엔티티를 조회하는 메서드
    private Calendar findCalendarByUserIdAndCalendarId(Long userId, Long calendarId) {
        return calendarRepository.findByUserIdAndId(userId, calendarId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID 또는 Calendar ID가 존재하지 않습니다.")
        );
    }
}
