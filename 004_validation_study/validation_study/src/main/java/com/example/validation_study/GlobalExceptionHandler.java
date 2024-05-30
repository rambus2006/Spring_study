package com.example.validation_study;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// com.example 패키지와 그 하위 패키지에 포함된 컨트롤러에서 발생하는 예외를 처리
// (단, 해당 컨트롤러에서 스스로 처리하는 예외가 있다면 해당 예외는 컨트롤러측에서 처리)

@RestControllerAdvice("com.example")
public class GlobalExceptionHandler {
    @ExceptionHandler(value=ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> globalArithmeticExceptionHandler(Exception ex) {
        System.out.println("Global arithmetic error handler");

        Map<String, Object> error = new HashMap<>();
        error.put("status", 400);
        error.put("message", ex.getMessage());
        error.put("global",true);

        return error;
    }

    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> globalExceptionHandler(HttpServletRequest request, Exception ex) throws Exception {
        System.out.println("from Global error handler");

        Map<String, Object> error = new HashMap<>();
        error.put("status", 500);
        error.put("message", ex.getMessage());

        return error;
    }
}