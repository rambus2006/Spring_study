package com.example.mvc;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

class SignUpForm {
    private String id;
    private String email;
    private String username;
    private int age;

    public String getId() {return id;}public void setId(String id) {this.id = id;}public String getEmail() {return email;}public void setEmail(String email) {this.email = email;}public String getUsername() {return username;}public void setUsername(String username) {this.username = username;}public int getAge() {return age;}public void setAge(int age) {this.age = age;}
    @Override public String toString() {return "SignUpForm{id='" + id + '\'' + ", email='" + email + '\'' + ", username='" + username + '\'' + ", age=" + age + '}'; }
}

// 커맨드 객체로 사용될 클래스 정의
class MyCommandObject {
    private String value1;
    private Integer value2;

    // 반드시 세터 메서드가 있어야 함
    public void setValue1(String value1) { this.value1 = value1; }
    public void setValue2(Integer value2) { this.value2 = value2; }

    @Override
    public String toString() {
        return "MyCommandObject{value1='" + value1 + '\'' + ", value2=" + value2 + '}';
    }
}

class MyJsonData {
    private String value1;
    private Integer value2;

    public String getValue1() { return value1; }
    public Integer getValue2() { return value2; }
    public void setValue1(String value1) { this.value1 = value1; }
    public void setValue2(Integer value2) { this.value2 = value2; }
}

// 내부 객체용 클래스 정의
class InnerObject {
    private String innerValue1;

    // 세터, 게터 추가
    public String getInnerValue1() {return innerValue1;}
    public void setInnerValue1(String innerValue1) {this.innerValue1 = innerValue1;}
}

class JsonDataWithArrayAndInnerObject {
    private List<String> array1; // String 형식의 데이터만 존재
    private List<Object> array2; // 다양한 데이터 형식이 혼재
    private InnerObject inner; // 내부 객체

    // 세터, 게터 추가
    public List<String> getArray1() {return array1;}
    public void setArray1(List<String> array1) {this.array1 = array1;}
    public List<Object> getArray2() {return array2;}
    public void setArray2(List<Object> array2) {this.array2 = array2;}
    public InnerObject getInner() {return inner;}
    public void setInner(InnerObject inner) {this.inner = inner;}
}


@RestController
@RequestMapping("/renew")
public class MyRenewController {
    @GetMapping(value = "/json-test-2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JsonDataWithArrayAndInnerObject jsonTest2(
            @RequestBody JsonDataWithArrayAndInnerObject jsonDataWithArrayAndInnerObject) {
        for(Object item : jsonDataWithArrayAndInnerObject.getArray2()) {
            if(item != null) System.out.println(item.getClass().getName());
        }
        return jsonDataWithArrayAndInnerObject;
    }

    @GetMapping(value = "/json-test",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String jsonTest(@RequestBody MyJsonData myJsonData) {
        System.out.println(myJsonData);
        return "hello";
    }


    /*
    @PostMapping("/test")
    public String commandObjectTest(@ModelAttribute MyCommandObject myCommandObject) {
        return myCommandObject.toString();
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute SignUpForm signUpForm) {
        System.out.println(signUpForm);
        return "success";
    }

    @GetMapping(value = "/echo", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String echo(@RequestBody String content) {
        return content;
    }

    @GetMapping(value = "/hello-html",
        produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String helloHTML() {
        return "<h1>Hello</h1>";
    }

    @GetMapping(value = "/echo-repeat", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String echoRepeat(
            @RequestParam("word") String word,
            @RequestHeader(value = "X-Repeat-Count", defaultValue = "1") Integer repeatCount) throws IOException {
        String result = "";
        for(int i=0;i<repeatCount;i++) {
            result += word;
        }
        return result;
    }

    @GetMapping(value = "/dog-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] dogImage() throws IOException {
        File file = ResourceUtils.getFile("classpath:static/dog.jpg");
        return Files.readAllBytes(file.toPath());
    }

    @GetMapping(value = "/dog-image-file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> dogImageFile() throws IOException {
        File file = ResourceUtils.getFile("classpath:static/dog.jpg");
        byte[] bytes = Files.readAllBytes(file.toPath());

        String filename = "dog.jpg";
        // 헤더 값 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
    */
}
