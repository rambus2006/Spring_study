package com.example.jpa;

import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/users") //전부 주소가 user로부터 실행
public class UserController {
    @Autowired
    private UserRepository userRepository; //이 객체도 자동 생성하는 객체를 생성해서 구현체를 지알아서 넣어준다.

    //우리는 쓰기만 하면된다.
    // C(create)
    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user) throws URISyntaxException { //@RequestBody는 Body로 받는 데이터를 받는다. user와 관련된 데이터 전달, 객체로 바뀜.
        user.setCreatedAt(new Date());  //정보를 새로 만들어서 넣어줌. id는 있으면 안됨. json에 일부만 주면 일부만 남아있고, 나머지는 null

        // 이후 성공하면 user 객체에 적절한 id가 삽입됨
        userRepository.save(user); //id는 db가 정해주는 것이기 때문에 save 전까지는 id를 알 수 없다. save 전에 getId 같은 메서드를 호출하면 null이 나온다. save 밑에 넣어주면 id가 나온다.

        return ResponseEntity
                .created(new URI("/users/" + user.getId()))
                .body(user);

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Location", "/users/" + user.getId());
//        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    // R
    //Iterable<T> 로 해놓은 이유는 제약을 받지 말라고 최상위 타입으로 설정해둔 것이다. 그래야 하위 타입까지 쓸 수 있기 때문에
    @GetMapping
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    // R
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
//        Optional<User> user = userRepository.findById(id);
//        // https://stackoverflow.com/questions/26550124/spring-returning-empty-http-responses-with-responseentityvoid-doesnt-work
//        // If returned value is Optional, there are convenient method, returned ok() or notFound():
//        return ResponseEntity.of(user);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id){
        Optional<User> op = userRepository.findById(id);
        if(op.isPresent()){
            return ResponseEntity.ok(op.get()); //값이 있으면 가져온다.
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    // U
    @PatchMapping("/{id}")
    public ResponseEntity<User> patchUser(@PathVariable("id") Integer id, @RequestBody User modified) {
        if(modified.getId() == null || modified.getId() != id) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsById(modified.getId())) {
            System.out.println(modified);
            userRepository.save(modified);
            return new ResponseEntity<>(modified, null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
    }

    // D
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id, HttpServletResponse response) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}