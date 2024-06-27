package com.example.jpa;

import jakarta.persistence.*;

// BaseEntity 상속
@Entity
public class MyEntity2 extends BaseEntity { //상속을 무조건 해야 한다. 실제로는 컬럼이 2개
    @Column Integer MyOwnColumn; //컬럼 새로 1개 생성
    @Embedded Address address; //Embedable은 필드처럼 들어가고, 쓸 때 @Embedded를 써야 한다. 실제로는 컬럼이 3개가 들어간다.
    //총 컬럼이 6개 생성된다.
}