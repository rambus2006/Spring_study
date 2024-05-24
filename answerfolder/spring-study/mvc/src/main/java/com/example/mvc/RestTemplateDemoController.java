package com.example.mvc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

class KanyeQuote {
    @JsonProperty("quote")
    private String quote;
}

@RestController
public class RestTemplateDemoController {

    @GetMapping(value = "/github/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String githubUser(@PathVariable("user") String user) {
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, null, HttpMethod.GET, URI.create("https://api.github.com/users/" + user));

        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        String responseBody = response.getBody();
        return responseBody;
    }

    @GetMapping(value = "/quote1", produces = MediaType.APPLICATION_JSON_VALUE)
    public String quote1() {
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, null, HttpMethod.GET, URI.create("https://api.kanye.rest"));

        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        String responseBody = response.getBody();
        return responseBody;
    }

    @GetMapping(value = "/quote2", produces = MediaType.APPLICATION_JSON_VALUE)
    public KanyeQuote quote2() {
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, null, HttpMethod.GET, URI.create("https://api.kanye.rest"));

        ResponseEntity<KanyeQuote> response = restTemplate.exchange(requestEntity, KanyeQuote.class);
        KanyeQuote responseBody = response.getBody();
        return responseBody;
    }



}









