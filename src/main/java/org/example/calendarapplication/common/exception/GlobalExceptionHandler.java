package org.example.calendarapplication.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice   // 여러 컨트롤러에서 발생한 예외를 한 곳에서 처리함
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)           // IllegalArgumentException이 발생하면 이 메서드에서 핸들링 하겠다는 의미
    public ResponseEntity<String> handleIllegal(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)   // HTTP 상태 코드를 401 Unauthorized로 설정함
                .body("에러 발생: " + ex.getMessage());          // 응답 Body에 예외 메시지를 포함
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)   // MethodArgumentNotValidException이 발생하면 이 메서드에서 핸들링 하겠다는 의미
    public ResponseEntity<String> handleValidate(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)   // HTTP 상태 코드를 400 Bad Request로 설정함
                .body("에러 발생: " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()); // 첫번째 에러만 선택하여 Body에 예외 메시지를 포함
    }
}

