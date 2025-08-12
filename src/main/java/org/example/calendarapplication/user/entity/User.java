package org.example.calendarapplication.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendarapplication.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {  // 작성일과 수정일 필드는 상속받았기 때문에 가지고 있음
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // 유저 ID
    private String username;    // 유저명
    private String email;       // 이메일

    // 생성자
    // id는 자동 생성되므로 생성자에서 받지 않음
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // 수정 메서드
    public void update(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
