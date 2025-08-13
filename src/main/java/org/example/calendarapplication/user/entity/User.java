package org.example.calendarapplication.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.calendarapplication.common.entity.BaseEntity;

@Entity                 // Bean으로 등록할 때 쓰는 annotation, 데이터베이스 테이블과 매핑하는데 사용함
@Getter                 // getter 메서드를 자동으로 생성해주는 annotation
@NoArgsConstructor      // 기본 생성자를 자동으로 생성해주는 annotation
public class User extends BaseEntity {  // 작성일과 수정일 필드는 상속받았기 때문에 가지고 있음
    @Id                                                     // 기본키로 지정함
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동으로 값을 증가시킴
    private Long id;            // 유저 ID

    @Column(nullable = false)   // 해당 데이터베이스 컬럼에는 null 값을 허용하지 않음
    private String username;    // 유저명

    @Column(unique = true, nullable = false)    // 해당 데이터베이스 컬럼은 중복될 수 없으며, null 값을 허용하지 않음
    private String email;       // 이메일

    @Column(nullable = false)   // 해당 데이터베이스 컬럼에는 null 값을 허용하지 않음
    private String password;    // 비밀번호

    // 생성자
    // id는 자동 생성되므로 생성자에서 받지 않음
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // 수정 메서드
    public void update(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
