package com.example.mvc;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

class Requestdata{
    private int num1;
    private int num2;

    public int getNum1() {return num1;}public void setNum1(int num1) {this.num1 = num1;}public int getNum2() {return num2;}public void setNum2(int num2) {this.num2 = num2;}

}
class Responsedata{
    private int result;
    public Responsedata(int result){
        this.result = result;
    }
    public int getResult() {return result;}public void setResult(int result) {this.result = result;}
}
class Person{
    private String name;
    private int age;

    public String getName() {return name;}public void setName(String name) {this.name = name;}public int getAge() {return age;}public void setAge(int age) {this.age = age;}


}
class MakePerson{
    private String name;
    private int age;

    public String getName() {return name;}public void setName(String name) {this.name = name;}public int getAge() {return age;}public void setAge(int age) {this.age = age;}


}
@RestController
public class SuhangController {
    @RequestMapping("/suhang")
    @PostMapping("/add")
    public int add(@RequestBody Responsedata Responsedata){
//        Requestdata r2 = new Requestdata();
//        int num1 = r2.getNum1();
//        int num2 = r2.getNum2();
//
//        int num3 = num1 + num2;
//        return num3;
        //int result = calcRequest.getNum1();

    }
//    @PostMapping("/add2")
//    public Map<String,Object> add2(@RequestBody Map<String,Object> request){
//        //int result = ((int))
//    }
    @PostMapping("/calc/{value}")
    //경로 설정
    public int Calcadd(
            @PathVariable(value = "add") String value,
            @RequestParam(value = "add") String add,
            @RequestHeader("API-Token") String apiToken
    )throws IOException{
        Requestdata r2 = new Requestdata();
        int num1 = r2.getNum1();
        int num2 = r2.getNum2();

        int num3 = num1 + num2;
        return num3;

    }
    //경로 설정
    public int Calcsub(
            @PathVariable(value = "sub") String value,
            @RequestParam(value = "sub") String sub,
            @RequestHeader("API-Token") String apiToken
    )throws IOException{
        Requestdata r2 = new Requestdata();
        int num1 = r2.getNum1();
        int num2 = r2.getNum2();

        int num3 = num1 - num2;
        return num3;

    }
//    @GetMapping("/make-person2?/{name}/{age}")
//    public int makeperson1(
//            @PathVariable(value = "name") String name,
//            @RequestParam(value = "age") int age,
//            @RequestHeader("API-Token") String apiToken
//
//
//    )throws IOException {
//        MakePerson person = new MakePerson();
//        name = person.getName();
//        age = person.getAge();
//        MakePerson.setResult(name,age);
//        return person;
//    }
//    @GetMapping("/make-person2?/{name}/{age}")
//    public int makeperson2(
//            @PathVariable(value = "name") String name,
//            @RequestParam(value = "age") int age,
//            @RequestHeader("API-Token") String apiToken
//
//
//    )throws IOException {
//        MakePerson person = new MakePerson();
//        name = person.getName();
//        age = person.getAge();
//        MakePerson.setResult(name,age);
//        return person;
//    }

}
