package com.example.mvc;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
@RestController
public class RestTemplateDemoController {

    @GetMapping(value = "/github/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
     String githubUser(@PathVariable("user") String user) {
        RestTemplate restTemplate = new RestTemplate();
         //요청 메시지에 바디 데이터가 없으므로 Void 타입으로 설정
        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, null, HttpMethod.GET, URI.create("https://api.github.com/users/" + user));
         //응답 메시지
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class); //바디데이터추출
         //응답 메시지의 바디 데이터를 문자열로 해석
        String responseBody = response.getBody();

        return responseBody;
    }
    @GetMapping(value = "/quote", produces = MediaType.APPLICATION_JSON_VALUE)
    public String quote() {
        RestTemplate restTemplate = new RestTemplate();
        //요청 메시지에 바디 데이터가 없으므로 Void 타입으로 설정
        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, null, HttpMethod.GET, URI.create("https://api.kanye.rest/"));
        //응답 메시지
        //데이터가 오긴 하는데 추출하기가 힘들어서 객체로 받아오는 방법을 더 선호한다.
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class); //바디데이터추출
        //응답 메시지의 바디 데이터를 문자열로 해석
        String responseBody = response.getBody();

        return responseBody;
    }
    @GetMapping(value = "/GithubJson", produces = MediaType.APPLICATION_JSON_VALUE)
    String GithubJson() {
        RestTemplate restTemplate = new RestTemplate();
        //요청 메시지에 바디 데이터가 없으므로 Void 타입으로 설정
        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, null, HttpMethod.GET, URI.create("https://api.github.com/users/" ));
        //응답 메시지
        //데이터가 오긴 하는데 추출하기가 힘들어서 객체로 받아오는 방법을 더 선호한다.
        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class); //바디데이터추출
        //응답 메시지의 바디 데이터를 문자열로 해석
//        String responseBody = response.getBody();

        return response.getBody();
    }

}