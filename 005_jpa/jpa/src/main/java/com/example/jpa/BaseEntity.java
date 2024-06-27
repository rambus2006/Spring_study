package com.example.jpa;
import jakarta.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {
    //현재 물려줄 필드가 2개(long, date타입변수)가 있다.
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;
}

