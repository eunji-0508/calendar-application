package org.example.calendarapplication.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.dto.CalendarCreateRequestDto;
import org.example.calendarapplication.dto.CalendarCreateResponseDto;
import org.example.calendarapplication.entity.Calendar;
import org.example.calendarapplication.repository.CalendarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
