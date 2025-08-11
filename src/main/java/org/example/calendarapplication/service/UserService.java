package org.example.calendarapplication.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.dto.*;
import org.example.calendarapplication.entity.User;
import org.example.calendarapplication.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 유저 생성 (Create)
    @Transactional
    public UserCreateResponseDto createUser(UserCreateRequestDto userCreateRequestDto) {
        User user = new User(userCreateRequestDto.getUsername(), userCreateRequestDto.getEmail());

        User savedUser = userRepository.save(user);

        return new UserCreateResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    // 전체 유저 조회 (Read)
    @Transactional(readOnly = true)
    public List<UserReadAllResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> new UserReadAllResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getModifiedAt(),
                user.getModifiedAt()
        )).collect(Collectors.toList());
    }

    // 유저 단건 조회 (Read)
    @Transactional(readOnly = true)
    public UserReadSingleResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID는 존재하지 않습니다.")
        );

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
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID는 존재하지 않습니다.")
        );

        user.update(userUpdateRequestDto.getUsername(), userUpdateRequestDto.getEmail());

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
        userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 User ID는 존재하지 않습니다.")
        );

        userRepository.deleteById(userId);
    }
}
