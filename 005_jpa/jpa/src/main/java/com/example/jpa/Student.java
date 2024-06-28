package com.example.jpa;

import jakarta.persistence.*;

@Entity
public class Student {
    @EmbeddedId
    private StudentId id;
    private String name;
}