//3307방민서 
package com.example.jpa;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/suhang")
public class ColorController {

    @Autowired
    private ColorRepository colorRepository; //이 객체도 자동 생성하는 객체를 생성해서 구현체를 지알아서 넣어준다.

    @GetMapping
    public void echo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getReader();

    }
    public char getRandomHexChar(){
        char[] hexChars = "0123456789abcdef".toCharArray();
        Random random = new Random();
        int randomIndex = random.nextInt(hexChars.length);
        return hexChars[randomIndex];
    }
    @PostMapping("/calc")
    public void addWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int num1;
        int num2;
        float op;



//        if(op=="add"){
//
//        }


    }


}
