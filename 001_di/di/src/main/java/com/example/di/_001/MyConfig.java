package com.example.di._001;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Bean을 등록해보자
@Configuration
public class MyConfig {
    // Bean 어노테이션을 사용하여 이름 변경 가능
    @Bean //직접 만들어서 리턴중
    public MyBean helloMyBean() { //메서드 이름을 따라간다.
        return new MyBean();
    }
    // 그냥 어노테이션만 쓰면 메서드 이름(여기서는 "myGreatString")을 Bean의 이름으로 사용함
    @Bean
    public String myGreatString() {
        return "Great"; //안에 내용은 Great가 들어간다.
    }
    // 메서드를 이용한 Bean 정의는 객체 생성자 수동 호출, 코드를 이용한 객체 설정 변경 작업이 필요한 경우 유용하게 사용 가능
    @Bean
    public Person myPerson() { //Bean 이름은 myPerson이다.
        Person p = new Person("John", 20);
        p.setSomething("important!");
        return p; //안에 내용은 객체 Person 이 들어간다.
    }
}
