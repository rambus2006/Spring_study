package com.example.mvc;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


class RequestData2{
    private String title;
    private String author;
    private int first_realese;
    private double rating;

    public String getTitle() {return title;}public void setTitle(String title) {this.title = title;}public String getAuthor() {return author;}public void setAuthor(String author) {this.author = author;}public int getFirst_realese() {return first_realese;}public void setFirst_realese(int first_realese) {this.first_realese = first_realese;}public double getRating() {return rating;}public void setRating(double rating) {this.rating = rating;}
}

class ResponseData2{
    private String result;
    private String id ;
    private boolean success;

    public String getResult() {
        return result;
    }

    public void setResult(String result, String a1234, boolean b) {
        this.result = result;
        this.id = a1234;
        this.success = b;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
@RestController
@RequestMapping("/test2")
public class Test2Controller {
    @GetMapping("/test2")
    public ResponseData2 test2(@RequestBody RequestData2 requestData2){
//        System.out.println(requestData2.getMessage());
        ResponseData2 responseData2 = new ResponseData2();
        responseData2.setResult("created","a1234",true);

         //왜 1001을 쓰는지 물어보기
        return responseData2;
    }

}
