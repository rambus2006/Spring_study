//package com.example.mvc;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.io.IOException;
//@Controller
//public class GetJson {
//    @RequestMapping(value="/Json",method= GetJson.GET)
//        public void Json(HttpServletRequest request,HttpServletResponse response) throws IOException {
//            response.setStatus(200);
//            response.setHeader("Content-Type","application/json; charset=utf-8");
//            response.getWriter().write("'result':'success','code':'1001'}");
//
//        }
//        public void GetJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
//            String method = request.getMethod();
//            System.out.println("Method : " + method);
//
//            String uri = request.getRequestURI();
//            System.out.println("URI : " + uri);
//
//            String query = request.getQueryString();
//            System.out.println();
//        }
//}
//
