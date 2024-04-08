package com.example.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  // Exception handler를 전체 시스템에 적용
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class)  // 이 spring web application에서 발생하는 모든 예외 잡기
    public ResponseEntity exception(Exception e) {  // 예외 발생 시 e에 예외 받음
        System.out.println(e.getClass().getName());
        System.out.println("---------------------------------");
        System.out.println(e.getLocalizedMessage());
        System.out.println("---------------------------------");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    // 특정 메서드의 예외 잡기 -> 해당 예외의 경우 위의 핸들러에 안잡힘
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {

        System.out.println("exception handler in global controller advice called");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
