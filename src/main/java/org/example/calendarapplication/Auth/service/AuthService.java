package org.example.calendarapplication.Auth.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.Auth.dto.AuthRequestDto;
import org.example.calendarapplication.Auth.dto.AuthResponseDto;
import org.example.calendarapplication.user.entity.User;
import org.example.calendarapplication.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    // 회원 가입
    @Transactional
    public void signup(AuthRequestDto authRequestDto) {
        User user = new User(authRequestDto.getUsername(), authRequestDto.getEmail(), authRequestDto.getPassword());

        userRepository.save(user);
    }
}
