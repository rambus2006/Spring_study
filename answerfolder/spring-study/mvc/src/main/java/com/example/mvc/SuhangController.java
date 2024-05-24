package com.example.mvc;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class CalcRequest {
    private int num1;
    private int num2;

    public int getNum1() {
        return num1;
    }
    public void setNum1(int num1) {
        this.num1 = num1;
    }
    public int getNum2() {
        return num2;
    }
    public void setNum2(int num2) {
        this.num2 = num2;
    }
}

class CalcResponse {
    private int result;

    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class User1 {
    private String username;
    private String password;

    public User1(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User1 user1 = (User1) o;
        return Objects.equals(username, user1.username) && Objects.equals(password, user1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}

class Book {
    private int id;
    private String name;
    private int price;
    private String description;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

@RestController
@RequestMapping("/suhang")
public class SuhangController {

    @PostMapping("/add")
    public CalcResponse add(@RequestBody CalcRequest calcRequest) {
        int result = calcRequest.getNum1() + calcRequest.getNum2();
        CalcResponse calcResponse = new CalcResponse();
        calcResponse.setResult(result);
        return calcResponse;
    }

    @PostMapping("/add2")
    public Map<String, Object> add2(@RequestBody Map<String, Object> request) {
        int result = ((int) request.get("num1")) + ((int) request.get("num2"));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);
        return resultMap;
    }

    @PostMapping("/calc/{op}")
    public CalcResponse calc(
            @RequestBody CalcRequest calcRequest,
            @PathVariable("op") String op) {
        int result = -1;
        if(op.equals("add")) result = calcRequest.getNum1() + calcRequest.getNum2();
        if(op.equals("sub")) result = calcRequest.getNum1() - calcRequest.getNum2();
        CalcResponse calcResponse = new CalcResponse();
        calcResponse.setResult(result);
        return calcResponse;
    }

    @GetMapping("/make-person1")
    public String makePerson1(@RequestParam("name") String name,
                              @RequestParam("age") int age) {
        Person p = new Person(name, age);
        return "success";
    }

    @GetMapping("/make-person2/{name}/{age}")
    public Person makePerson2(@PathVariable("name") String name,
                              @PathVariable("age") int age) {
        Person p = new Person(name, age);
        return p;
    }

    User1 userData = new User1("admin", "1234");
    @PostMapping("/login")
    public String login(@RequestHeader("X-Username") String username,
                        @RequestHeader("X-Password") String password) {
//        User1 u = new User1(username, password);
//        if(userData.equals(u)) return "success";
//        else return "fail";
        if(userData.getUsername().equals(username) && userData.getPassword().equals(password)) {
            return "success";
        } else {
            return "fail";
        }
    }

    int id = 0;
    ArrayList<Book> books = new ArrayList<>();
    @PostMapping("/books")
    public Map<String, Object> addBook(@RequestBody Book newBook) {
        id++;
        newBook.setId(id);
        books.add(newBook);
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        return result;
    }

    @GetMapping("/books")
    public ArrayList<Book> getBooks(@RequestParam(value="category",
            required=false, defaultValue="all") String category) {
        if(category.equals("all")) return books;
        else {
            ArrayList<Book> filtered = new ArrayList<>();
            for(Book b : books) {
                if(b.getCategory().equals(category)) {
                    filtered.add(b);
                }
            }
            return filtered;
        }
    }

}










