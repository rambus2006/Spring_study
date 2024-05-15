package com.example.mvc;

//1.스프링 부트의 핵심 클래스들을 import 해서 불러오기
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //2. 이 어노테이션이 적용된 클래스가 스프링 애플리케이션의 시작점이 된다.
public class MvcApplication {
//서버가 새로고침 하게 하기
	//3. 메인 메서드
	public static void main(String[] args) {
		//3-1. 콘솔에 메시지 출력
		System.out.println("Hello");
		//3-2. 스프링 부트 애플리케이션실행, MvcApplication 클래스가 애플리케이션의 시작 클래스임을 지정
		SpringApplication.run(MvcApplication.class, args);
	}

}
