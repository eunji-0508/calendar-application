package org.example.calendarapplication.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.calendarapplication.comment.dto.CommentCreateRequestDto;
import org.example.calendarapplication.comment.dto.CommentCreateResponseDto;
import org.example.calendarapplication.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController         // Bean으로 등록할 때 쓰는 annotation, @ResponseBody + @Controller (Restful API를 작성하기 위해서 사용함)
@RequiredArgsConstructor   // 필수(final) 필드만을 매개변수로 하는 생성자를 자동으로 생성함
public class CommentController {
    // 스프링 컨테이너로부터 의존성을 주입받는 싱글톤 CommentService 객체
    // 싱글톤: 어떤 클래스를 하나의 객체로 만드는 것
    private final CommentService commentService;

    /*
    기능: 댓글 생성 (Create)
    HTTP Method: POST
    URL: /users/{userId}/calendars/{calendarId}/comments (http://localhost:8080/users/{userId}/calendars/{calendarId}/comments)
    인증/인가 : 로그인 필요
    Request Body: CommentCreateResponseDto
    Response: 성공 시 생성된 댓글 정보를 JSON 형태로 반환함
    */
    @PostMapping("/users/{userId}/calendars/{calendarId}/comments")
    public ResponseEntity<CommentCreateResponseDto> createComment(
            // @PathVariable: URL로 전달된 값을 파라미터로 받아오는 역할을 함
            @PathVariable Long userId,
            @PathVariable Long calendarId,
            @RequestBody CommentCreateRequestDto commentCreateRequestDto    // HTTP 요청 Body에 담긴 JSON 데이터를 CommentCreateRequestDto 객체로 변환함
    ) {
        // 새로운 댓글을 생성하고, 생성된 댓글 정보를 HTTP 200 OK와 함께 반환함
        return ResponseEntity.ok(commentService.createComment(userId, calendarId, commentCreateRequestDto));
    }
}
