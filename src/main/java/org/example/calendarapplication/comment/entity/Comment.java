package org.example.calendarapplication.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendarapplication.calendar.entity.Calendar;
import org.example.calendarapplication.user.entity.User;

@Entity                 // Bean으로 등록할 때 쓰는 annotation, 데이터베이스 테이블과 매핑하는데 사용함
@Getter                 // getter 메서드를 자동으로 생성해주는 annotation
@NoArgsConstructor      // 기본 생성자를 자동으로 생성해주는 annotation
public class Comment {
    @Id                                                     // 기본키로 지정함
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동으로 값을 증가시킴
    private Long id;                                        // 댓글 ID

    @Column(nullable = false)   // 해당 데이터베이스 컬럼에는 null 값을 허용하지 않음
    private String content;     // 댓글 내용

    @ManyToOne(fetch = FetchType.LAZY)                      // N:1 관계
    @JoinColumn(name = "user_id", nullable = false)         // 외래키 컬럼명을 user_id로 하고, null 값을 허용하지 않음
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)                      // N:1 관계
    @JoinColumn(name = "calendar_id", nullable = false)     // 외래키 컬럼명을 calendar_id로 하고, null 값을 허용하지 않음
    private Calendar calendar;

    // 생성자
    // id는 자동 생성되므로 생성자에서 받지 않음
    public Comment(String content, User user, Calendar calendar) {
        this.content = content;
        this.user = user;
        this.calendar = calendar;
    }
}
