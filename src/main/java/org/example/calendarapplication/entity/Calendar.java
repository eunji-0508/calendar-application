package org.example.calendarapplication.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Calendar extends BaseEntity {  // 작성일과 수정일 필드는 상속받았기 때문에 가지고 있음
    // 필드
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String username;    // 작성 유저명
    private String title;       // 할일 제목
    private String content;     // 할일 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
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
