package com.example.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class ProblemSolveController {
    // 1.
    @GetMapping("/get_test")
    public ResponseEntity<String> get_test() {
        String responseBody = "GET";
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/get_test")
    public ResponseEntity<String> post_test() {
        String responseBody = "POST";
        return ResponseEntity.ok(responseBody);
    }
}

@RestController
class MessageController {
    private String message = "";

    @PatchMapping("/update_message/{message}")
    public String updateMessage(@PathVariable("message") String newmessage) {
        message = newmessage;
        return message;
    }
}

@RestController
class CalculatorController {
    @PostMapping("/calc")
    public HashMap<String, Object> calculate(
            @RequestParam("num1") int num1,
            @RequestParam("num2") int num2,
            @RequestParam("op") String op) {
        HashMap<String, Object> h1 = new HashMap<>();
        int result = -1;
        if (op.equals("+")) result = num1 + num2;
        if (op.equals("-")) result = num1 - num2;
        if (op.equals("/")) result = num1 / num2;
        if (op.equals("*")) result = num1 * num2;

        h1.put("num1", num1);
        h1.put("num2", num2);
        h1.put("op", op);
        h1.put("result", result);
        return h1;
    }

    private int visitCount = 0;

    @PostMapping("/update_visit_count")
    public String visit() {
        visitCount += 1;
        return String.valueOf(visitCount);
    }

    @PostMapping("/update_visit_count_json")
    public HashMap<String, Object> visitController() {
        visitCount++;
        HashMap<String, Object> result = new HashMap<>();
        result.put("visit_count", visitCount);
        return result;
    }

    private ArrayList<String> email = new ArrayList<>();

    @PostMapping("/register_email")
    public HashMap<String, Object> register_email(@RequestBody String[] emails) {
        for (String emailStr : emails) {
            email.add(emailStr);
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("email_addresses", email);
        return result;
    }
}

class Vote {
    private String option;
    private int count;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

@RestController
class VoteHandler {
    private ArrayList<Vote> agenda = new ArrayList<>();
    private HashMap<String, Object> result = new HashMap<>();

    @PatchMapping("/vote/register_option")
    public HashMap<String, Object> register(@RequestBody Vote vote) {
        agenda.add(vote);
        result.put("agenda", agenda);
        return result;
    }

    @GetMapping("/vote/show_options")
    public HashMap<String, Object> showOptions() {
        result.put("agenda", agenda);
        return result;
    }

    @PostMapping("/vote/make_vote")
    public HashMap<String, Object> makeVote(@RequestBody String option) {
        for (Vote v : agenda) {
            if (v.getOption().equals(option)) {
            }
        }
        return null;
    }
}