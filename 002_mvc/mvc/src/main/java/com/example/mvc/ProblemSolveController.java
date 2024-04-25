package com.example.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class ProblemSolveController {
    //1.
    @GetMapping("/get_test")
    public ResponseEntity<String> get_test(){
        String responseBody = "GET";
        return ResponseEntity.ok(responseBody);
    }
    @PostMapping("/get_test")
    public ResponseEntity<String> post_test(){
        String responseBody = "POST";
        return ResponseEntity.ok(responseBody);
    }


}

@RestController
class MessageController{
    private String message = "";

    //쿼리 스트링으로 풀기
//    public void updateMessage1(@RequestParam("message")String message ){
//        this.message = message;
//    }


    @PatchMapping("/update_message/{message}")
    //pathVariable로 보낸 요청값을 가져온다.
    public String updateMessage(@PathVariable("message") String newmessage){
        message = newmessage; //요청값을 message에 넣는다.
        return message; //메시지값을 리턴한다.
    }

}
//만드는 방식이 hashmap과 class를 만들어서 getter,setter 하는 것 이렇게 2가지가 있다.
@RestController
class CalculatorController {
    //3번 문제
    @PostMapping("/calc")
    public HashMap<String, Object> calculate(
            @RequestParam("num1") int num1,
            @RequestParam("num2") int num2,
            @RequestParam("op") String op) {
        HashMap<String,Object> h1 = new HashMap<String,Object>();

        int result = -1;
        if(op.equals("+")) result = num1 + num2;
        if(op.equals("-")) result = num1 - num2;
        if(op.equals("/")) result = num1 / num2;
        if(op.equals("*")) result = num1 * num2;

        h1.put("num1", num1);
        h1.put("num2", num2);
        h1.put("op", op);
        h1.put("result", result);
        return h1;
    }

    //4번
    private int visitCount = 0;

    @PostMapping("/update_visit_count")
    public String visit() {
        visitCount+=1;
        return "" + visitCount;
        // return String.valueOf(visitCount);
    }
    //5번
    @PostMapping("/update_visit_count_json")
    public HashMap<String, Object> visitController(){
        visitCount++;
        HashMap<String,Object> result = new HashMap<>();
        result.put("visit_count",visitCount);
        return result;
    }

    ArrayList<String> email = new ArrayList<>();
    //6번
    @PostMapping("/register_email")
    public HashMap<String,Object> register_email(@RequestBody String emails) {
        System.out.println(emails);
        HashMap<String,Object> result = new HashMap<>();
        String[] mailstring = emails.split(",");
        for(int i=0;i<mailstring.length;i++){
            email.add(mailstring[i]);
        }
        result.put("email_addresses", email);
        return result;
    }

}
class Vote{
    public String option;
    public int count;

    public String getOption() {return option;}public void setOption(String option) {this.option = option;}public int getCount() {return count;}public void setCount(int count) {this.count = count;}
}
@RestController
class VoteHandler{
    //안에 있는 객체를 넣는 거
    ArrayList<Object> agenda = new ArrayList<>();
    //바깥쪽의 result
    HashMap<Object,Object> result  = new HashMap<>();
    //7번
    //안건 등록
    @PatchMapping("/vote/register_option")
    public HashMap<Object,Object> register(@RequestBody String option,int count){


    }
    @GetMapping("/vote/show_options")
    @PostMapping("/vote/make_vote")
}
