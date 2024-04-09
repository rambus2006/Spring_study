package com.example.di._001;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyCalculatorService {
    // 해당 필드(calculator)에 의존성 주입이 진행될 수 있도록 Autowired 어노테이션을 적용
    @Autowired //Application context에서 타입이 Calculateor인 Bean을 찾는다.
    private Calculator calculator;

    public int calcAdd(int a, int b) {
        return calculator.add(a, b);
    }
}
