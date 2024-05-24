package com.example.validation_study;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Member implements Serializable {
    @NotBlank
    @Size(min=2, max=10)
    private String name;

    @Positive
    @Min(1)
    @Max(130)
    private int age;

    @NotNull @NotBlank @Email
    private String email;

    @Pattern(regexp="[a-zA-Z1-9]{4,8}", message="비밀번호 양식이 틀렸습니다.")
    private String password;

    private String gender;
    @AssertTrue(message="성별은 여성이거나 남성이어야 합니다.")
    public boolean isValidGender(){
        if(gender == null) return false;
        return gender.equals("남성") || gender.equals("여성");
    }

    private ArrayList<String> hobbies = new ArrayList<String>();
    private ArrayList<String> interests = new ArrayList<String>();
}






