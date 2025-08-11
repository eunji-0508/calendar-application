package org.example.calendarapplication.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.dto.UserCreateRequestDto;
import org.example.calendarapplication.dto.UserCreateResponseDto;
import org.example.calendarapplication.dto.UserReadAllResponseDto;
import org.example.calendarapplication.entity.User;
import org.example.calendarapplication.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
}
