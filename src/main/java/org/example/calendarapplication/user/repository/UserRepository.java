package org.example.calendarapplication.user.repository;

import org.example.calendarapplication.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository에 @Repository 포함되어 있기 때문에 안써도 됨
// <클래스명, 기본키>
public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 User를 조회하는 메서드
    Optional<User> findByEmail(String email);
}
