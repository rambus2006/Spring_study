package com.example.jpa;

import org.springframework.data.repository.CrudRepository;

//인터페이스를 만든다.
//제네릭에서의 타입 2개 : 첫번째는 엔티티 클래스의 타입, 두번째는 id 의 타입
public interface UserRepository extends CrudRepository<User, Integer> {

}