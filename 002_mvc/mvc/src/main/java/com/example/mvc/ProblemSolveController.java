package com.example.mvc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void visit(@RequestHeader("/update_visit_count") String host) {
        ++visitCount;
        return visitCount;
    }
    }
}
