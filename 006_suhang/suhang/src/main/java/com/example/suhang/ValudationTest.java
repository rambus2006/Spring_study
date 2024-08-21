package com.example.suhang;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ValudationTest {
    @NotBlank
    private String hello;

    @Positive
    @Max(150)
    @Min(0)
    private Integer id;
}
