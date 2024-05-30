package com.example.validation_study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// 따로 에러 처리와 관련된 핸들러가 정의되지 않은 컨트롤러 (따라서 전역적으로 예외 처리)
@RestController
@RequestMapping("/another")
public class AnotherController {
    @GetMapping("/error_classcast")
    @ResponseBody
    public String doClassCast() {
        Object o = "Hello";
        return ((Integer) o) + "";
    }

    @GetMapping("/error_arithmetic")
    @ResponseBody
    public String doArithmetic() {
        return (10 / 0) + "";
    }
}