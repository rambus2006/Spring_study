package com.example.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyCalculatorService {
    @Autowired
    private Calculator calculator;

    /*
    @Autowired
    public MyCalculatorService(Calculator calculator) {
        System.out.println("from constructor");
        System.out.println(calculator);
        this.calculator = calculator;
    }
    */
    public int calcAdd(int a, int b) {
        return calculator.add(a, b);
    }
}
