package com.example.mvc;

import org.springframework.web.bind.annotation.*;

/*
@RequestBody로 변환할 데이터의 모습

{
    "message": "world",
    "from": "@mirim_1",
    "to": "yubs",
    "importance": 5
}
*/
class RequestData {
    private String message;
    private String from;
    private String to;
    private int importance;

    public String getMessage() {return message;}public void setMessage(String message) {this.message = message;}public String getFrom() {return from;}public void setFrom(String from) {this.from = from;}public String getTo() {return to;}public void setTo(String to) {this.to = to;}public int getImportance() {return importance;}public void setImportance(int importance) {this.importance = importance;}
}

/*
@ResponseBody로 변환할 데이터의 모습

{
    "result": "success",
    "code": 1001
}
*/
class ResponseData {
    private String result;
    private int code;

    // 게터, 세터
    public String getResult() {return result;}public void setResult(String result) {this.result = result;}public int getCode() {return code;}public void setCode(int code) {this.code = code;}
}

class BookInfo {
    private String title;
    private String author;
    private int first_release;
    private double rating;
    public String getTitle() {return title;}public void setTitle(String title) {this.title = title;}public String getAuthor() {return author;}public void setAuthor(String author) {this.author = author;}public int getFirst_release() {return first_release;}public void setFirst_release(int first_release) {this.first_release = first_release;}public double getRating() {return rating;}public void setRating(double rating) {this.rating = rating;}
}

class BookCreatedResult {
    private String result;
    private String id;
    private boolean success;

    public String getResult() {return result;}public void setResult(String result) {this.result = result;}public String getId() {return id;}public void setId(String id) {this.id = id;}public boolean isSuccess() {return success;}public void setSuccess(boolean success) {this.success = success;}
}

class TextRepeatRequest {
    private String text;
    private int count;

    public String getText() {return text;}public void setText(String text) {this.text = text;}public int getCount() {return count;}public void setCount(int count) {this.count = count;}
}

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test1")
    @ResponseBody
    public ResponseData test1(@RequestBody RequestData requestData) {
        System.out.println(requestData.getMessage());
        ResponseData responseData = new ResponseData();
        responseData.setResult("success");
        responseData.setCode(1001);
        return responseData;
    }

    @GetMapping("/test2")
    @ResponseBody
    public BookCreatedResult test2(@RequestBody BookInfo bookInfo) {
        System.out.println(bookInfo.getTitle());
        BookCreatedResult result = new BookCreatedResult();
        result.setResult("created");
        result.setId("a1234");
        result.setSuccess(true);
        return result;
    }

    @GetMapping("/test3")
    @ResponseBody
    public String test3(@RequestBody TextRepeatRequest textRepeatRequest) {
        String text = textRepeatRequest.getText();
        int count = textRepeatRequest.getCount();
        String result = "";
        for(int i=0;i<count;i++) result += text;
        return result;
    }


}
