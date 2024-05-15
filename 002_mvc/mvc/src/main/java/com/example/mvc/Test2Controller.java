package com.example.mvc;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//클라이언트로부터 요청된 데이터를 나타낸다.(전송)
class RequestData2{
    private String title;
    private String author;
    private int first_realese;
    private double rating;

    public String getTitle() {return title;}public void setTitle(String title) {this.title = title;}public String getAuthor() {return author;}public void setAuthor(String author) {this.author = author;}public int getFirst_realese() {return first_realese;}public void setFirst_realese(int first_realese) {this.first_realese = first_realese;}public double getRating() {return rating;}public void setRating(double rating) {this.rating = rating;}
}

//응답데이터를 나타낸다. (답장)
class ResponseData2{
    //처리 결과
    private String result;
    //어떤 책을 만들었는지 식별하기 위한 ID
    private String id ;
    //요청의 성공 여부
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
//요청을 처리하는 컨트롤러
@RestController
//test2 경로일 때 응답한다.
@RequestMapping("/test2")
public class Test2Controller {
    //get 요청일 때 처리
    @GetMapping("/test2")
    public ResponseData2 test2(@RequestBody RequestData2 requestData2){
//        System.out.println(requestData2.getMessage());
        //객체 생성
        ResponseData2 responseData2 = new ResponseData2();
        //setResult 메서드를 사용해 처리 경과, id,성공 여부(답장)을 설정한다.
        responseData2.setResult("created","a1234",true);

         //왜 1001을 a1234로 변경하냐면 사용자가 입력한 값을 그대로 반환하기 위해서이다.

        //객체 반환
        return responseData2;
    }

}
