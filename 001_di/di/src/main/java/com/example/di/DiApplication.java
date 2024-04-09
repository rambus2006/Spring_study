package com.example.di;

import com.example.di._001.Calculator;
import com.example.di._001.MyCalculatorService;
import com.example.di._001.Person;
import com.example.di._002.ComplexCrudRepositoryImpl;
import com.example.di._002.MyDatabaseService;
import com.example.di._002.SimpleCrudRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiApplication {

	public static void main(String[] args){
		ApplicationContext context = SpringApplication.run(DiApplication.class, args);
	//[ 어떤 bean 이 있는 지 확인해보자 ]
		// 모든 Bean들의 이름 가져와서
		String[] beanNames = context.getBeanDefinitionNames();
		// 확인해보기
		for (String beanName : beanNames) {
			//Bean을 만들어서 확인해보려고 한다.
			// 단, 기본적으로 스프링에서 제공하는 Bean들은 가급적 필터링해서 보여주기 (전부 다 걸러지진 않음)
			//org로 시작하는 것, spring으로 시작하는 것들을 거르는 코드
			if(!beanName.startsWith("org.") && !beanName.startsWith("spring.")) {
				System.out.println(beanName);
			}
		}
//	[등록한 Bean을 사용해보자]
		Person p = (Person) context.getBean("myPerson"); //타입 추론이 아제 가능하다. (var p) 가능
		p.sayName();

		//왜 이렇게 하는지 알아야 함! + autowired도 꼭 써줘야 한다. Bean은 맨 앞에만 소문자로 바꿔준다.
		MyCalculatorService myCalculatorService = (MyCalculatorService) context.getBean("myCalculatorService");
		System.out.println(myCalculatorService.calcAdd(3,4)); //7

		Calculator calculator = (Calculator) context.getBean("calculator");
		System.out.println(calculator.add(5,4)); //9

		MyDatabaseService myDatabaseService = (MyDatabaseService) context.getBean("myDatabaseService"); //Bean을 구하는 메서드
		System.out.println(myDatabaseService.simpleRepository instanceof SimpleCrudRepositoryImpl); //true  //instanceof - 클래스의 타입을 구하는 함수
		System.out.println(myDatabaseService.complexRepository instanceof ComplexCrudRepositoryImpl); //true
	}

}
