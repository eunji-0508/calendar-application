package org.example.calendarapplication.repository;

import org.example.calendarapplication.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository에 @Repository 포함되어 있기 때문에 안써도 됨
// <클래스명, 기본 키>
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
