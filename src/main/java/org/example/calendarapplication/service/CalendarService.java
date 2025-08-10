package org.example.calendarapplication.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.dto.CalendarCreateRequestDto;
import org.example.calendarapplication.dto.CalendarCreateResponseDto;
import org.example.calendarapplication.dto.CalendarReadAllResponseDto;
import org.example.calendarapplication.entity.Calendar;
import org.example.calendarapplication.repository.CalendarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    // 일정 생성 (Create)
    @Transactional
    public CalendarCreateResponseDto createCalendar(CalendarCreateRequestDto calendarCreateRequestDto) {
        Calendar calendar = new Calendar(
                calendarCreateRequestDto.getUsername(),
                calendarCreateRequestDto.getTitle(),
                calendarCreateRequestDto.getContent()
                );

        Calendar savedCalendar = calendarRepository.save(calendar);

        return new CalendarCreateResponseDto(
                savedCalendar.getId(),
                savedCalendar.getUsername(),
                savedCalendar.getTitle(),
                savedCalendar.getContent(),
                savedCalendar.getCreatedAt(),
                savedCalendar.getModifiedAt()
        );
    }

    // 전체 일정 조회 (Read)
    @Transactional(readOnly = true)
    public List<CalendarReadAllResponseDto> getAllCalendars() {
        List<Calendar> calendars = calendarRepository.findAll();

        return calendars.stream().map(calendar -> new CalendarReadAllResponseDto(
                calendar.getId(),
                calendar.getUsername(),
                calendar.getTitle(),
                calendar.getContent(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        )).collect(Collectors.toList());
    }
}
