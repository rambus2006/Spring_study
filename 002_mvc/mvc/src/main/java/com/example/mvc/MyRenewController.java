package com.example.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Enumeration;

//원래는 클래스 하나 파서 만들어야 한다.
// 커맨드 객체로 사용될 클래스 정의
//클라이언트로부터의 데이터를 담는다.
class MyCommandObject {
    //필드
    private String value1;
    private Integer value2;

    // 반드시 세터 메서드가 있어야 함***
    //데이터를 받아 커맨드 객체를 만든다.
    public void setValue1(String value1) { this.value1 = value1; }
    public void setValue2(Integer value2) { this.value2 = value2; }

    //한줄로 만들기 : ctrl+shift+j
    @Override
    public String toString() {return "MyCommandObject{value1='" + value1 + '\'' + ", value2=" + value2 + '}';}
}

//컨트롤러 클래스
@RestController
//경로 설정
@RequestMapping("/renew")
public class MyRenewController {

    //</echo>
    // produces 옵션을 통해서 미디어 타입 지정 가능 (유추해서 자동으로 지정하게 할 수도 있지만 가급적 써주는 것을 권장)
    //get요청 처리
    @GetMapping(value = "/echo", produces = MediaType.TEXT_PLAIN_VALUE) //주소는 /renew/echo이다. 어노테이션 기본값은 value라 "/echo"만 써도 된다.
    // * produces = MediaType.TEXT_PLAIN_VALUE : 응답에 대한 타입을 알려준다. 생략해도되지만 쓰는게 낫다.

    //ResponseBody와 RequestBody는 많이 쓰이니 꼭 외워두자 
    // 반환한 문자열이 바로 응답 메시지의 바디 데이터에 삽입될 수 있도록 @ResponseBody 어노테이션 추가 (@RestController를 사용하면 생략 가능)
    @ResponseBody //echo 메서드가 반환하는 값이  ResponseBody로 들어간다는 것을 의미한다.
    public String echo(@RequestBody byte[] content) {  //요청에 있는 값이 @RequestBody로 배열로 들어간다. content : 내용들이 byte[]로 들어간다는 것을 의미
        // 메서드 정보 접근할 필요 없음 (GET 메서드)
        // 주소 정보 접근할 필요 없음 (@PathVariable 사용)
        // 쿼리 스트링 정보 접근할 필요 없음 (@RequestParam 사용)
        // 프로토콜, HTTP 버전 정보 접근할 필요가 보통 없음
        // 헤더 정보 접근할 필요 없음 (@RequestHeader 사용)
        // 요청 메시지의 바디 데이터 접근은 @RequestBody 어노테이션을 이용해서 전달받을 수 있음
        //-> 코드가 엄청 줄어든다.
        String bytesToString = new String(content, StandardCharsets.UTF_8); //문자열로 변환한다. (Text/Plain)으로 들어간다.
        System.out.println(bytesToString);

        // Content-Type 헤더의 경우 produces 옵션을 제공하여 미디어 타입 지정 가능
        return bytesToString; //@ResponseBody로 내용이 들어간ㄷ.
    }
    //get요청이 .renew/hello 경로로 올 때 이 메서드가 처리한다.
    @RequestMapping(value="/hello", method=RequestMethod.GET)
    //경로 설정
    @GetMapping("/echo")
    // 요청과 응답 객체를 파라미터로 받는 메서드를 정의한다.
    public void echo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 메서드 정보 접근
        String method = request.getMethod();//요청한 http 메서드를 얻어온다.
        System.out.println("Method : " + method); //http 메서드를 콘솔에 출력한다.

        // 주소 정보 접근
        String uri = request.getRequestURI();
        System.out.println("URI : " + uri);

        // 쿼리 스트링 정보 접근
        String query = request.getQueryString();
        System.out.println("Query String : " + query);

        // 프로토콜, HTTP 버전 정보 접근
        String protocol = request.getProtocol();
        System.out.println("Protocol : " + protocol);

        // 헤더 정보 접근
        System.out.println("Headers");
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames != null && headerNames.hasMoreElements()) {
            String h = headerNames.nextElement();
            System.out.println(h + " : " + request.getHeader(h));
        }

        // 요청 메시지의 바디 데이터 접근
        // 1. 바디 데이터를 추가하기 위해서는 POSTMAN과 같은 클라이언트 프로그램을 사용해야 함
        // 2. 일반적으로, GET 요청은 바디 데이터를 추가하지 않는 것이 권장되며, 데이터를 보내기 위해서는 보통 쿼리스트링을 이용함
        //바이트 배열을 utf-8 문자열로 반환한다.
        byte[] bytes = request.getInputStream().readAllBytes();
        String bytesToString = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(bytesToString);

        // 응답 헤더 설정(content-type 설정)
        response.setHeader("Content-Type", "text/plain; charset=utf-8");
        // 전달받은 body 텍스트를 그대로 응답하도록 설정
        response.getWriter().write(bytesToString);

    }
    @GetMapping(value = "/echo-repeat", produces = MediaType.TEXT_PLAIN_VALUE)
// @RequestHeader 어노테이션을 통해서 X-Repeat-Count에 적힌 숫자 정보 가져오고 없으면 1로 초기화
    public String echoRepeat(@RequestParam("word") String word, @RequestHeader(value = "X-Repeat-Count", defaultValue = "1") Integer repeatCount) throws IOException {
        String result = "";
        for(int i=0;i<repeatCount;i++) {
            result += word;
        }
        return result;
    }
    //타입 지정을 빼면 옥텟 스트링(임의의 바이너리 데이터)
    @GetMapping(value = "/dog-image", produces = MediaType.IMAGE_JPEG_VALUE) //타입을 알려준다.
    public byte[] dogImage() throws IOException {
        // resources 폴더의 static 폴더에 이미지 있어야 함
        File file = ResourceUtils.getFile("classpath:static/dog.jpg");
        // 파일의 바이트 데이터 모두 읽어오기
        byte[] bytes = Files.readAllBytes(file.toPath()); //return byte[] bytes = Files.readAllBytes(file.toPath()); 이렇게 쓰면 한줄을 줄일 수 있다.

        return bytes;
    }
    //<> : 제네릭 (T 타입을 byte로 설정) - 공부하기
    /*
    * ResponseEntity<>로도 넣을 수 있다.
    * */
    @GetMapping(value = "/dog-image-file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
// 헤더를 직접 조정하고 싶은 경우 ResponseEntity 타입을 반환하도록 설정 가능
// (꺽쇠 안에는 응답 메시지의 바디 데이터에 포함될 타입을 지정)
    public ResponseEntity<byte[]> dogImageFile() throws IOException { //byte가 바디 데이터에 들어가는 데이터 타입 - 이해 안되면 문제 풀기
        File file = ResourceUtils.getFile("classpath:static/dog.jpg");
        byte[] bytes = Files.readAllBytes(file.toPath());

        String filename = "dog.jpg";
        // 헤더 값 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK); //바디, 헤더, 시작줄  - 구체적으로 쓰려면 이방법을 쓰자
    }
//    @PostMapping("/test")
    // @ModelAttribute를 타입 앞에 붙여주고 메서드의 파라미터 값으로 전달되게 함
    //폼, 쿼리 데이터에 있는 값을 가져오기 위해
    //
//    public String commandObjectTest(@ModelAttribute MyCommandObject myCommandObject) {
//        return myCommandObject.toString();
//    }
}
