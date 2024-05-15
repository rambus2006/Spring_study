package com.example.mvc;
//02_다양한 HTTP 요청을 처리하는 컨트롤러 클래스
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.stream.Collectors;

//클래스가 spring mvc 컨트롤러임을 나타냄
@Controller
//@RequestMapping("/api") //밑에 있는 여러개의 핸들러 메서드 앞에 붙는 경로 (localhost:1234/api/hello로 접속해야 한다.
public class MyController {
    //일을 처리하는 (핸들러)메서드 (반드시 어노테이션 붙여놔야 한다.)- 만약 요청의 주소가 hello 이고,get 메서드로 요청이 오면 이 메서드가 처리한다.
//    @RequestMapping(value = "/hello",method = RequestMethod.GET) 아래에 있는 GetMapping 과 같다.

    //get,post 메서드같은 것들을 핸들러 메서드라고 한다.
    //[1. hello 메서드] : httlo 라는 html을 반환한다.
    @GetMapping("/Hello") //앞에 메서드 명이 붙어있다.(경로)
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException { //request는 요청, response(아무것도 없다)응답을 파라미터로 받는다.메서드가 끝나면 응답이 알아서 내용이 채워져서 간다.
        response.setStatus(200); //특별히 저장을 하지 않으면 200을 응답코드로 준다.
        response.setHeader("Content-Type","text/html; charset=utf-8"); //얘는 헤더인데, 설명하고 있는 내용은 바디에 있다.
        //쌤수업내용을 나의 언어로 설명할 수 있어야 한다.
        response.getWriter().write("<h1>Hello</h1>"); // 바디에 들어간다. Reader랑 Writer 메서드(입출력)보충

    }

    //핸들러메서드2
        //[2.echo 메서드]:요청한 다양한 정보들을 콘솔에 출력하고, 요청 바디를 그대로 응답으로 반환한다.
        @GetMapping("/echo")
        public void echo(HttpServletRequest request,HttpServletResponse response) throws IOException {
            String method = request.getMethod();
            System.out.println("Method : " + method);

            String uri = request.getRequestURI();
            System.out.println("url : " + uri);

            //localhost:1234/hell?ascf
            String query = request.getQueryString();
            System.out.println("Query String : "+ query);

            String protocol = request.getProtocol();
            System.out.println("Protocol : " + protocol);

            // 헤더 정보 접근
            System.out.println("Headers");
            //반복문이다.
            Enumeration<String> headerNames = request.getHeaderNames();
            while(headerNames != null && headerNames.hasMoreElements()) {
                String h = headerNames.nextElement();
                System.out.println(h + " : " + request.getHeader(h)); //h가 헤더 이름, request.getHeader(h)가 헤더 내용이다
            }

            //텍스트 인코딩 - 가변바이트,
            //문자열로 받는 코드
            byte[] bytes = request.getInputStream().readAllBytes();
            String bytesToString = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(bytesToString);

            response.setHeader("Content-Type", "text/plain; charset=utf-8");
            // 전달받은 body 텍스트를 그대로 응답하도록 설정
            response.getWriter().write(bytesToString);
        }

    //[3. hello-xml] : xml 형식의 "Hello"메시지를 반환
    @GetMapping("/hello-xml")
    public void helloXML(HttpServletResponse response) throws IOException {
        // 상태 코드와 관련된 상수를 제공하므로 이용해도 무방함
        response.setStatus(HttpStatus.OK.value());
        // "text/xml"이 아님을 유의
        response.setHeader("Content-Type", "application/xml; charset=utf-8");
        response.getWriter().write("<text>Hello</text>");
    }
    //[4.hello-json] : json 형식의 "Hello"메시지를 반환
    @GetMapping("/hello-json")
    public void helloJSON(HttpServletResponse response) throws IOException {
        // 성공적으로 리소스를 찾아서 돌려주면서 404 코드를 돌려줘도, 스프링 쪽에서는 속사정을 알 방법이 없으니 허용하고 잘 동작함
        // (리소스가 존재하지 않는 이유를 json 같은걸로 설명할 수도 있으므로, HTTP 스펙 상에서도 204와는 달리, 404 코드를 돌려줄 때
        // 바디 데이터를 포함하지 않아야 된다고 명시하지 않았음. 단, 웹 브라우저에서는 404이므로 문제라고 인식함, 그렇다고 해도 4XX
        // 에러에 대한 처리는 프로그래머가 해야 함)
        response.setStatus(404);
        // "text/json"이 아님을 유의
        response.setHeader("Content-Type", "application/json");
        response.getWriter().write("{ \"data\": \"Hello\" }");
    }
    //[5.echo-repeat]:쿼리 스트링 정보를 반복하여 응답으로 반환한다.
    @GetMapping("/echo-repeat")
    public void echoRepeat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setHeader("Content-Type", "text/plain");
        // 커스텀 헤더인 X-Repeat-Count에 적힌 숫자 정보 가져오고 없으면 1로 초기화
        int loopCount = Integer.parseInt(request.getHeader("X-Repeat-Count") == null ? "1" : request.getHeader("X-Repeat-Count"));
        // 쿼리 스트링 정보 가져와서(asdf-aaa&hello=1234&world 를 &로 끊어서
        String query = request.getQueryString();
        // 쿼리 스트링 나누고
        String[] querySplit = query.split("&");
        String result = "";

        // 각 쿼리 스트링 정보들을 X-Repeat-Count만큼 반복해서 보여주기
        for(String s : querySplit) {
            for(int i=0;i<loopCount;i++) {
                String[] tmp = s.split("=");
                result += tmp[0] + "," + tmp[1] + "\n";
            }
        }

        response.getWriter().write(result.trim());
    }
    //[6.dog-image] :  static 폴더의 dog.jpg 이미지를 응답으로 반환한다.
    @GetMapping("/dog-image")
    public void dogImage(HttpServletResponse response) throws IOException {
        // resources 폴더의 static 폴더에 이미지 있어야 함
        File file = ResourceUtils.getFile("classpath:static/dog.jpg");
        // 파일의 바이트 데이터 모두 읽어오기
        byte[] bytes = Files.readAllBytes(file.toPath());

        response.setStatus(200);
        // 응답 메시지의 데이터가 JPEG 압축 이미지임을 설정
        response.setHeader("Content-Type", "image/jpeg");
        // 바이트 데이터 쓰기 (여기서는 텍스트 데이터를 전송하지 않기 떄문에 Writer 대신 OutputStream을 이용)
        response.getOutputStream().write(bytes);
    }
    //[7.dogImageFile]:static 폴더의 dog.jpg 이미지를 다운로드 파일로 응답합니다.
    @GetMapping("/dog-image-file")
    public void dogImageFile(HttpServletResponse response) throws IOException {
        File file = ResourceUtils.getFile("classpath:static/dog.jpg");
        byte[] bytes = Files.readAllBytes(file.toPath());

        response.setStatus(200);
        // 임의의 바이너리 데이터임을 알려주는 MIME 타입 설정
        response.setHeader("Content-Type", "application/octet-stream"); //임의의 바이트로 보내는 것 (아까는 바이너리파일인데 이미지인데,이번에는 바이너리 파일인데 어떤건지 모르는 것)
        // Content-Length는 자동으로 파일 크기만큼 설정해주지만 여기서는 그냥 넣었음
        // (Q) 만약 바이트 크기를 제대로 주지 않으면 어떻게 될까?)
//        response.setHeader("Content-Length", bytes.length + ""); //스프링이 알아서 넣어주기 때문에 주석처리
        // 다운로드 될 파일 이름 설정
        String filename = "dog.jpg";
        //Content-Disposition : 바로 파일을 다운로드 하게 한다.
        //attachment; filename=" + filename: 브라우저에서 받을 파일의 이름
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.getOutputStream().write(bytes);
        //다음시간에 post 부터 시작
    }
    //2024-03-22
    //단어 추가
    private ArrayList<String> wordList = new ArrayList<>();

    // [8. addWord] : 위의 ArrayList에 단어를 추가하는 메서드
    @PostMapping("/words")
    public void addWord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bodyString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String[] words = bodyString.split("\n");
        for(String w : words) wordList.add(w.trim());

        // 201 CREATED 응답 코드 발생시키기
        response.setStatus(201); //create
        // 생성된 리소스를 확인할 수 있는 URL 알려주기 (Location 헤더 굳이 안 붙여도 기능적으로 차이는 없음)
        response.setHeader("Location", "/words");
    }

    // [9.showWords] : 저장된 모든 단어 보여주기
    @GetMapping("/words")
    public void showWords(HttpServletResponse response) throws IOException {
        String allWords = String.join(",", wordList);

        response.setStatus(200);
        response.getWriter().write(allWords);
    }
    // 단어 삭제하기
    /*
    * - "/words" 에 DELETE 요청을 보내면
      - worldList 의 모든 단어를 삭제하고(clear 메서드 호출하면 된다)
      - 204 응답을 보내는 핸들러 메서드 작성
      - post  로 단어를 추가하고 get으로 확인하고 delete로 지우고 다시 get 해서 다 비워졌는지 확인
    * */
    //[10.deleteWords]:wordList의 모든 단어를 삭제하고 상태코드 204를 반환
    /* 상태코드204 : 작업은 성공하였고 바디에 포함된 정보는 없다는 의미(delete 했을때)
    */
    @DeleteMapping("/words")
    public void deleteWords(HttpServletResponse response){
        wordList.clear();
        response.setStatus(204);
    }
    //[11.getProducts 메서드]:username, productId, 쿼리 스트링 show_comments, 헤더 API-Token을 처리하여 조건에 맞는 응답을 반환합니다. (유효한 API-Token이 없는 경우 401 상태 코드)
    @GetMapping("/users/{username}/products/{productId}")
    public void getProducts(
            @PathVariable(value = "username", required = true) String username,
            @PathVariable("productId") Integer productId,
            //@PathVariable("productId") Integer productId,

            @RequestParam(value = "show_comments", required = false, defaultValue = "true") Boolean showComments, //
            @RequestHeader("API-Token") String apiToken,
            HttpServletResponse response) throws IOException {

        System.out.println(username);
        System.out.println(productId);
        System.out.println(showComments);
        System.out.println(apiToken);

        if(!apiToken.equals("secret")) {
            response.setStatus(401);
            response.getWriter().write("need valid api key");
        } else {
            response.setStatus(200);
            response.getWriter().write("success");
        }
    }



}
