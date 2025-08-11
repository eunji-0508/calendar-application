package org.example.calendarapplication.repository;

import org.example.calendarapplication.entity.Calendar;
import org.example.calendarapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository에 @Repository 포함되어 있기 때문에 안써도 됨
// <클래스명, 기본 키>
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    // 해당 User의 모든 일정(Calendar) 리스트를 조회하는 메서드
    List<Calendar> findAllByUser(User user);
}
