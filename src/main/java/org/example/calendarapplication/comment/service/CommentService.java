package org.example.calendarapplication.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.calendar.entity.Calendar;
import org.example.calendarapplication.calendar.repository.CalendarRepository;
import org.example.calendarapplication.comment.dto.CommentCreateRequestDto;
import org.example.calendarapplication.comment.dto.CommentCreateResponseDto;
import org.example.calendarapplication.comment.entity.Comment;
import org.example.calendarapplication.comment.repository.CommentRepository;
import org.example.calendarapplication.user.entity.User;
import org.example.calendarapplication.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service                        // Bean으로 등록할 때 쓰는 annotation
@RequiredArgsConstructor        // 필수(final) 필드만을 매개변수로 하는 생성자를 자동으로 생성함
public class CommentService {
    // 스프링 컨테이너로부터 의존성을 주입받는 싱글톤 CommentRepository, CalendarRepository, UserRepository 객체
    // 싱글톤: 어떤 클래스를 하나의 객체로 만드는 것
    private final CommentRepository commentRepository;
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentCreateResponseDto createComment(Long userId, Long calendarId, CommentCreateRequestDto commentCreateRequestDto) {
        // 유저 ID가 일치하는 User 엔티티를 조회함
        // 일치하는 User 엔티티가 없으면 예외를 발생시킴
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID는 존재하지 않습니다.")
        );

        // 캘린더 ID가 일치하는 User 엔티티를 조회함
        // 일치하는 Calendar 엔티티가 없으면 예외를 발생시킴
        Calendar calendar = calendarRepository.findById(calendarId).orElseThrow(
                () -> new IllegalArgumentException("해당 Calendar ID는 존재하지 않습니다.")
        );

        // Comment 엔티티를 생성함
        Comment comment = new Comment(
                commentCreateRequestDto.getContent(),
                user,
                calendar
        );

        // save 메서드를 활용하여 Comment 엔티티를 데이터베이스에 저장하고, 저장된 객체를 반환함
        Comment savedComment = commentRepository.save(comment);

        // 응답 DTO을 생성하고 반환함
        return new CommentCreateResponseDto(
                savedComment.getId(),
                savedComment.getContent(),
                userId,
                calendarId
        );
    }
}
