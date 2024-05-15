package com.example.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import static jdk.internal.agent.Agent.getText;

//(전송)
//클라이언트로부터 요청된 데이터를 나타낸다. 요청에 포함된 텍스트와 반복횟수를 저장한다.
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
//클라이언트에 반환될 응답 데이터를 나타낸다. ( 답장)
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

//@RestController가 있어서 이 클래스가 컨트롤러 클래스라고 표시한다.
@RestController
//경로 설정
@RequestMapping("/test")
public class HelloControllter {
    //get메서드일 때 경로 설정
    @GetMapping("/test3")
    //문자열로 리턴할 때는 public String을 해준다.

    public String test3(@RequestBody HelloJson requestData3){
//        HelloJson responseData3 = new HelloJson();
//        HelloJson textRequest;
        // 위의 클라이언트 객체에서 값을 가져온다.

        // 만약 텍스트가 hello이고 count가 3이면 result 에는 hellohellohello가 찍힌다.
        // 클라이언트가 보낸 요청 데이터에서 텍스트를 가져온다.HelloJson객체(위에있음)의 getText()메서드를 호출해서 텍스트를 가져와서 text 변수에 저장한다.
        String text =requestData3.getText();
        //클라이언트가 보낸 요청 데이터에서 반복 횟수를 가져온다.
        int count = requestData3.getCount();
        // text 변수에 저장된 텍스트를 count 만큼 반복해서 result  문자열에 추가한다.
        String result="";
        for(int i=0;i<count;i++) result = result + text;
        // text 변수에 저장된 텍스트를 반환한다
        return result;

    }
}
