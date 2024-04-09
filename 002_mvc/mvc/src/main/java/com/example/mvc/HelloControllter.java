package com.example.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import static jdk.internal.agent.Agent.getText;

class HelloJson{
    private String text;
    private int count;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
class ResponseHello{
    private String result;

    public String getResult() {
        return result = "HelloHelloHello";
    }

    public void setResult(String result) {
        this.result = result;
    }

    //생성자 생성 꼭 해주기
    public ResponseHello(String result) {
        this.result = result;
    }
}

@RestController
@RequestMapping("/test")
public class HelloControllter {
    @GetMapping("/test3")
    //문자열로 리턴할 때는 public String을 해준다.
    public String test3(@RequestBody HelloJson requestData3){
//        HelloJson responseData3 = new HelloJson();
//        HelloJson textRequest;
        String text =requestData3.getText();
        int count = requestData3.getCount();
        String result="";
        for(int i=0;i<count;i++) result = result + text;
        return text;

    }
}
