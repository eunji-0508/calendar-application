package org.example.calendarapplication.calendar.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.calendar.dto.*;
import org.example.calendarapplication.calendar.entity.Calendar;
import org.example.calendarapplication.calendar.repository.CalendarRepository;
import org.example.calendarapplication.user.entity.User;
import org.example.calendarapplication.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final UserService.UserRepository userRepository;

    // 일정 생성 (Create)
    @Transactional
    public CalendarCreateResponseDto createCalendar(Long userId, CalendarCreateRequestDto calendarCreateRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID는 존재하지 않습니다.")
        );

        Calendar calendar = new Calendar(
                calendarCreateRequestDto.getTitle(),
                calendarCreateRequestDto.getContent(),
                user
                );

        Calendar savedCalendar = calendarRepository.save(calendar);

        return new CalendarCreateResponseDto(
                savedCalendar.getId(),
                savedCalendar.getTitle(),
                savedCalendar.getContent(),
                savedCalendar.getCreatedAt(),
                savedCalendar.getModifiedAt()
        );
    }

    // 전체 일정 조회 (Read)
    @Transactional(readOnly = true)
    public List<CalendarReadAllResponseDto> getAllCalendars(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID는 존재하지 않습니다.")
        );

        List<Calendar> calendars = calendarRepository.findAllByUser(user);

        return calendars.stream().map(calendar -> new CalendarReadAllResponseDto(
                calendar.getId(),
                calendar.getTitle(),
                calendar.getContent(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        )).collect(Collectors.toList());
    }

    // 일정 단건 조회 (Read)
    @Transactional(readOnly = true)
    public CalendarReadSingleResponseDto getCalendar(Long userId, Long calendarId) {
        Calendar calendar = calendarRepository.findByUserIdAndId(userId, calendarId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID 또는 Calendar ID가 존재하지 않습니다.")
        );

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
        Calendar calendar = calendarRepository.findByUserIdAndId(userId, calendarId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID 또는 Calendar ID가 존재하지 않습니다.")
        );

        calendar.update(calendarUpdateRequestDto.getTitle(), calendarUpdateRequestDto.getContent());

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
        calendarRepository.findByUserIdAndId(userId, calendarId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID 또는 Calendar ID가 존재하지 않습니다.")
        );

        calendarRepository.deleteById(calendarId);
    }
}
