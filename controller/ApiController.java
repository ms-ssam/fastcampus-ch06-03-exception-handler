package com.example.exception.controller;

import com.example.exception.dto.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class ApiController {
    @GetMapping("")
    public User get(@RequestParam(required = false) String name, @RequestParam(required = false) int age) {
        // required = false -> 해당 request parameter 없어도 동작하도록. 단, 해당 값은 null
        User user = new User();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;  // nullPointerException occur!

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user) {
        System.out.println(user);
        return user;
    }

    // 컨트롤러에 Exception handler 작성하면 우선순위는 이게 global exception handler보다 높음
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {

        System.out.println("exception handler in controller called");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
