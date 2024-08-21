package com.example.di;

import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name;
    private int age;

    public Person() {
        System.out.println("Person() called!");
    }

    public Person(String name, int age) {
        System.out.println("Person(String name, int age) called!");
        this.name = name;
        this.age = age;
    }

    public void sayName() {
        System.out.println(this.name);
    }

    public void setSomething(String important) {
        // do some jobs...
    }
}
