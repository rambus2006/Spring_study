package com.example.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvcApplication {
//서버가 새로고침 하게 하기
	public static void main(String[] args) {
		System.out.println("Hello");
		SpringApplication.run(MvcApplication.class, args);
	}

}
