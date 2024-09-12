package group.again;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TestController {
    //2가지(메서드(어노테이션) + 주소) : 메서드가 실행된다.
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    //요청 바디에 있는 JSON을 자바 객체로 변환하기 위해서

    //1. 메서드 인자로 @RequestBody 어노테이션을 붙여줘야 한다.
    //2. JSON으로 객체로 변환하기 위해 클래스로 만들어줘야 한다.이때 클래스 타입으로 JSON이 변환되고, 뒤에 있는 클래스에 저장된다.
    //   (Data data:클래스명 클래스타입) - 이때 데이터와 관련있는 클래스이름을 지어줘야 한다.
    //   원래 @GetMapping(value = "/test2"consumes = MediaType.APPLICATION_JSON_VALUE)으로 타입을 알려줘야 한다.(생략가능)
    @GetMapping("/test2")
    @ResponseBody
    public ResponseData test2(@RequestBody RequestData data){
        ResponseData reponseData = new ResponseData();
        reponseData.setMessage("my message");
        return reponseData;
    }

    @PostMapping("/power")
    @ResponseBody
    public ResponseResult test3(@RequestBody RequestData2 requestData) {
        ResponseResult responseResult = new ResponseResult();
        int j=1;
        for(int i=1;i<=requestData.getPow();i++){
            j*=requestData.getNum();
        }
        responseResult.setResult(j);
        return responseResult;
    }
    @PostMapping("/average")
    @ResponseBody
    public ReponseAverage test4(@RequestBody RequestData3 averageData){
        ReponseAverage reponseAverage = new ReponseAverage();
        ArrayList<Integer> list = averageData.getLi();
        int an=0;
        float result=0;
        System.out.println(list.size());
        for(int i=0;i<list.size();i++) {
            an += list.get(i);
        }
        result = (((float)an)/list.size());
        reponseAverage.setNums(result);
        return reponseAverage;
    }

}
