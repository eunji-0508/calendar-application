package org.example.calendarapplication.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)   // 이 에러에 대해서는 여기서 핸들링 하겠다
    public ResponseEntity<String> handleIllegal(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("에러 발생: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)   // 이 에러에 대해서는 여기서 핸들링 하겠다
    public ResponseEntity<String> handleValidate(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("에러 발생: " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}

