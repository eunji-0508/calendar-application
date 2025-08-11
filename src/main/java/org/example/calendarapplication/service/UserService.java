package org.example.calendarapplication.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.dto.UserCreateRequestDto;
import org.example.calendarapplication.dto.UserCreateResponseDto;
import org.example.calendarapplication.entity.User;
import org.example.calendarapplication.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                savedUser.getEmail()
        );
    }
}
