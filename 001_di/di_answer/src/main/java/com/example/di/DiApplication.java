package com.example.di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DiApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DiApplication.class, args);

		String[] beanNames = context.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			if(!beanName.startsWith("org.") && !beanName.startsWith("spring.")) {
				System.out.println(beanName);
			}
		}

		Person p = (Person) context.getBean("myPerson");
		p.sayName();

		MyCalculatorService myCalculatorService = (MyCalculatorService) context.getBean("myCalculatorService");
		System.out.println(myCalculatorService.calcAdd(3, 4));

		Calculator calculator = (Calculator) context.getBean("calculator");
		System.out.println(calculator.add(3, 4));

		MyDatabaseService myDatabaseService = (MyDatabaseService) context.getBean("myDatabaseService");
		System.out.println(myDatabaseService.simpleRepository instanceof SimpleCrudRepositoryImpl);
		System.out.println(myDatabaseService.complexRepository instanceof ComplexCrudRepositoryImpl);

	}

}
