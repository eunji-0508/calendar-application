package org.example.calendarapplication.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.user.dto.*;
import org.example.calendarapplication.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController             // Bean으로 등록할 때 쓰는 annotation, @RequestBody + @Controller (Restful API를 작성하기 위해서 사용함)
@RequiredArgsConstructor       // 필수(final) 필드만을 매개변수로 하는 생성자를 자동으로 생성함
public class UserController {
    // 스프링 컨테이너로부터 의존성을 주입받는 싱글톤 UserService 객체
    // 싱글톤: 어떤 클래스를 하나의 객체로 만드는 것
    private final UserService userService;

    /*
    기능: 전체 유저 조회 (Read)
    HTTP Method: GET
    URL: /users (http://localhost:8080/users)
    인증/인가 : 로그인 필요
    Response: 성공 시 모든 유저를 JSON 형태 리스트로 반환함
    */
    @GetMapping("/users")
    public ResponseEntity<List<UserReadAllResponseDto>> getAllUsers() {
        // 모든 유저를 가져와 HTTP 200 OK와 함께 반환함
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /*
    기능: 유저 단건 조회 (Read)
    HTTP Method: GET
    URL: /users/{userId} (http://localhost:8080/users/{userId})
    인증/인가 : 로그인 필요
    Response: 성공 시 특정 유저의 정보를 JSON 형태로 반환함
    */
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserReadSingleResponseDto> getUser(
            // @PathVariable: URL로 전달된 값을 파라미터로 받아오는 역할을 함
            @PathVariable Long userId
    ) {
        // 특정 유저의 정보를 가져와 HTTP 200 OK와 함께 반환함
        return ResponseEntity.ok(userService.getUser(userId));
    }

    /*
    기능: 유저 수정 (Update)
    HTTP Method: PATCH
    URL: /users/{userId} (http://localhost:8080/users/{userId})
    인증/인가 : 로그인 필요
    Response: 성공 시 해당 유저의 수정된 정보를 JSON 형태로 반환함
    */
    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserUpdateResponseDto> updateUser(
            // @PathVariable: URL로 전달된 값을 파라미터로 받아오는 역할을 함
            @PathVariable Long userId,
            // HTTP 요청 Body에 담긴 JSON 데이터를 UserUpdateRequestDto 객체로 변환함
            @RequestBody UserUpdateRequestDto userUpdateRequestDto
    ) {
        // 해당 유저의 정보를 수정하고, 수정된 정보를 HTTP 200 OK와 함께 반환함
        return ResponseEntity.ok(userService.updateUser(userId, userUpdateRequestDto));
    }

    /*
    기능: 유저 삭제 (Delete)
    HTTP Method: DELETE
    URL: /users/{userId} (http://localhost:8080/users/{userId})
    인증/인가 : 로그인 필요
    Response: 삭제 성공 시 HTTP 200 OK를 반환하고, Body는 없음
    */
    @DeleteMapping("/users/{userId}")
    public void deleteUser(
            // @PathVariable: URL로 전달된 값을 파라미터로 받아오는 역할을 함
            @PathVariable Long userId
    ) {
        // 해당 유저를 삭제함
        userService.deleteUser(userId);
    }
}
