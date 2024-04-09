package com.example.mvc;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//가져와야하는 데이터를 클래스로 만든다.
class RequestData{
    /*
    {
        "message" : "world",
        "from" : "@Mirim_1",
        "to" : "yubs",
        "importance" : "importance"
    }
    */
    private String message;
    private String from ;
    private String to;
    private String importance;

    //ctrl + shift + j 하면 한줄로 만들 수 잇다.
    public String getMessage() {return message;}public void setMessage(String message) {this.message = message;}public String getFrom() {return from;}public void setFrom(String from) {this.from = from;}public String getTo() {return to;}public void setTo(String to) {this.to = to;}public String getImportance() {return importance;}public void setImportance(String importance) {this.importance = importance;}
}
class ResponseData{
    private String result;
    private int code;

    public String getResult() {return result;}public void setResult(String result) {this.result = result;}public int getCode() {return code;}public void setCode(int code) {this.code = code;}
}

@RestController
@RequestMapping ("/test")
public class TestController {
    @GetMapping("/test1")
    public ResponseData test1(@RequestBody RequestData requestData){
        System.out.println(requestData.getMessage());
        ResponseData responseData = new ResponseData();
        responseData.setResult("success");
        responseData.setCode(1001);
        return responseData;
    }
}
