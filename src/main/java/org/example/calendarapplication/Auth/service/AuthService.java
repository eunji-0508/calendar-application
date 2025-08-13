package org.example.calendarapplication.Auth.service;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.Auth.dto.AuthLoginRequestDto;
import org.example.calendarapplication.Auth.dto.AuthLoginResponseDto;
import org.example.calendarapplication.Auth.dto.AuthSignUpRequestDto;
import org.example.calendarapplication.common.config.PasswordEncoder;
import org.example.calendarapplication.user.entity.User;
import org.example.calendarapplication.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service                    // Bean으로 등록할 때 쓰는 annotation
@RequiredArgsConstructor    // 필수(final) 필드만을 매개변수로 하는 생성자를 자동으로 생성함
public class AuthService {
    // 스프링 컨테이너로부터 의존성을 주입받는 싱글톤 UserRepository 객체
    // 싱글톤: 어떤 클래스를 하나의 객체로 만드는 것
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    // 회원 가입
    @Transactional
    public void signup(AuthSignUpRequestDto authSignUpRequestDto) {
        String encodedPassword = passwordEncoder.encode(authSignUpRequestDto.getPassword());

        // DTO로부터 전달받은 데이터를 통해 User 엔티티를 생성함
        User user = new User(
                authSignUpRequestDto.getUsername(),
                authSignUpRequestDto.getEmail(),
                encodedPassword);

        // save 메서드를 활용하여 User 엔티티를 데이터베이스에 저장함
        userRepository.save(user);
    }

    // 로그인
    @Transactional
    public AuthLoginResponseDto login(AuthLoginRequestDto authLoginRequestDto) {
//        // 이메일과 패스워드가 일치하는 User 엔티티를 조회함
//        // 일치하는 User 엔티티가 없으면 예외를 발생시킴
//        User user = userRepository.findByEmailAndPassword(authLoginRequestDto.getEmail(), authLoginRequestDto.getPassword()).orElseThrow(
//                () -> new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.")
//        );
//
//        User user = userRepository.findByEmail(authLoginRequestDto.getEmail());

        if(authLoginRequestDto.getPassword().e)

        boolean success = passwordEncoder.matches(authLoginRequestDto.getPassword(), encodedPassword);

        if(!success) {
            throw new IllegalArgumentException("비밀번호가 옳지 않습니다.");
        }

        // user의 id를 이용하여 응답 DTO을 생성하고 반환함
        return new AuthLoginResponseDto(user.getId());
    }
}
