package com.example.jpa;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String country;
    private String city;
    private String addr;
}