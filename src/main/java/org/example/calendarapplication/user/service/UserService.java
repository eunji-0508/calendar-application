package org.example.calendarapplication.user.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.user.dto.*;
import org.example.calendarapplication.user.entity.User;
import org.example.calendarapplication.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service                     // Bean으로 등록할 때 쓰는 annotation
@RequiredArgsConstructor     // 필수(final) 필드만을 매개변수로 하는 생성자를 자동으로 생성함
public class UserService {
    // 스프링 컨테이너로부터 의존성을 주입받는 싱글톤 UserRepository 객체
    // 싱글톤: 어떤 클래스를 하나의 객체로 만드는 것
    private final UserRepository userRepository;

    // 전체 유저 조회 (Read)
    @Transactional(readOnly = true)     // 읽기 전용
    public List<UserReadAllResponseDto> getAllUsers() {
        // findAll 메서드를 활용하여 모든 유저 정보를 리스트로 가져옴
        List<User> users = userRepository.findAll();

        // 응답 DTO로 변환하여 리스트로 반환함
        return users.stream().map(user -> new UserReadAllResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getModifiedAt(),
                user.getModifiedAt()
        )).collect(Collectors.toList());
    }

    // 유저 단건 조회 (Read)
    @Transactional(readOnly = true)     // 읽기 전용
    public UserReadSingleResponseDto getUser(Long userId) {
        // 유저 ID가 일치하는 User 엔티티를 조회함
        // 일치하는 User 엔티티가 없으면 예외를 발생시킴
        User user = findUserById(userId);

        // user의 정보를 이용하여 응답 DTO을 생성하고 반환함
        return new UserReadSingleResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 수정 (Update)
    @Transactional
    public UserUpdateResponseDto updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        // 유저 ID가 일치하는 User 엔티티를 조회함
        // 일치하는 User 엔티티가 없으면 예외를 발생시킴
        User user = findUserById(userId);

        // User 엔티티의 유저명(username)과 이메일(email)을 요청 DTO의 값으로 수정함
        user.update(userUpdateRequestDto.getUsername(), userUpdateRequestDto.getEmail());

        // user의 정보를 이용하여 응답 DTO을 생성하고 반환함
        return new UserUpdateResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 삭제 (Delete)
    @Transactional
    public void deleteUser(Long userId) {
        // 유저 ID가 일치하는 User 엔티티를 조회함
        // 일치하는 User 엔티티가 없으면 예외를 발생시킴
        findUserById(userId);

        // userId에 해당하는 User 엔티티를 데이터베이스에서 삭제함
        userRepository.deleteById(userId);
    }

    // 주어진 userId로 User 엔티티를 조회하는 메서드
    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID는 존재하지 않습니다.")
        );
    }
}
