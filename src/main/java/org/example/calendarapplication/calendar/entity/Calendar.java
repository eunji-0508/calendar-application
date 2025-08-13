package org.example.calendarapplication.calendar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendarapplication.common.entity.BaseEntity;
import org.example.calendarapplication.user.entity.User;

@Entity             // Bean으로 등록할 때 쓰는 annotation, 데이터베이스 테이블과 매핑하는데 사용함
@Getter             // getter 메서드를 자동으로 생성해주는 annotation
@NoArgsConstructor  // 기본 생성자를 자동으로 생성해주는 annotation
public class Calendar extends BaseEntity {  // 작성일과 수정일 필드는 상속받았기 때문에 가지고 있음
    // 필드
    @Id                                                     // 기본키로 지정함
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동으로 값을 증가시킴
    private Long id;            // 일정 ID

    @Column(nullable = false)   // 해당 데이터베이스 컬럼에는 null 값을 허용하지 않음
    private String title;       // 할일 제목

    private String content;     // 할일 내용

    // 연관 관계
    @ManyToOne(fetch = FetchType.LAZY)                  // N:1 관계
    @JoinColumn(name = "user_id", nullable = false)     // 외래키 컬럼명을 user_id로 하고, null 값을 허용하지 않음
    private User user;

    // 생성자
    // id는 자동 생성되므로 생성자에서 받지 않음
    public Calendar(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 수정 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
