package com.example.mvc;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

class CalcResult {
    private Integer num1;
    private Integer num2;
    private String op;
    private Integer result;

    public Integer getNum1() {return num1;}public void setNum1(Integer num1) {this.num1 = num1;}public Integer getNum2() {return num2;}public void setNum2(Integer num2) {this.num2 = num2;}public String getOp() {return op;}public void setOp(String op) {this.op = op;}public Integer getResult() {return result;}public void setResult(Integer result) {this.result = result;}
}

@RestController
public class ProblemSolveController {
    // 1.
    @GetMapping("/get_test")
    public String getTest() {
        return "GET";
    }
    @PostMapping("/post_test")
    public String postTest() {
        return "POST";
    }

    // 2.
    private String message = "";

    // /update_message?message=hello
    @PatchMapping("/update_message")
    public void updateMessage1(@RequestParam("message") String message) {
        this.message = message;
    }

    // /update_message/hello
    // /update_message/world
    @PatchMapping("/update_message/{message}")
    public void updateMessage2(@PathVariable("message") String message) {
        this.message = message;
    }

    // 3.
    // /calc?num1=1&num2=2&op=%2B
    @PostMapping("/calc")
    public CalcResult calc(@RequestParam("num1") Integer num1,
                          @RequestParam("num2") Integer num2,
                          @RequestParam("op") String op) {
        int result = -1;
        if(op.equals("+")) result = num1 + num2;
        if(op.equals("-")) result = num1 - num2;
        CalcResult calcResult = new CalcResult();
        calcResult.setNum1(num1);
        calcResult.setNum2(num2);
        calcResult.setOp(op);
        calcResult.setResult(result);
        return calcResult;
    }

    // 4.
    private int visitCount = 0;

    @PostMapping("/update_visit_count")
    public String updateVisitCount1() {
        visitCount++;
        // return "" + visitCount;
        return String.valueOf(visitCount);
    }

    // 5.
    @PostMapping("/update_visit_count_json")
    public HashMap<String, Object> updateVisitCount2() {
        visitCount++;
        HashMap<String, Object> result = new HashMap<>();
        result.put("visit_count", visitCount);
        return result;
    }



}
































