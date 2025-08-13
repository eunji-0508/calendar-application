package org.example.calendarapplication.Auth.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.Auth.dto.AuthLoginRequestDto;
import org.example.calendarapplication.Auth.dto.AuthLoginResponseDto;
import org.example.calendarapplication.Auth.dto.AuthSignUpRequestDto;
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
    public void signup(AuthSignUpRequestDto authSignUpRequestDto) {
        User user = new User(authSignUpRequestDto.getUsername(), authSignUpRequestDto.getEmail(), authSignUpRequestDto.getPassword());

        userRepository.save(user);
    }

    // 로그인
    @Transactional(readOnly = true)
    public AuthLoginResponseDto login(AuthLoginRequestDto authLoginRequestDto) {
        User user = userRepository.findByEmailAndPassword(authLoginRequestDto.getEmail(), authLoginRequestDto.getPassword()).orElseThrow(
                () -> new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.")
        );

        return new AuthLoginResponseDto(user.getId());
    }
}
