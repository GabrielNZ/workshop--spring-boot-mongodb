package com.gabrielnz.workshopmongodb.controller;

import com.gabrielnz.workshopmongodb.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User user1 = new User("1","Maria","123456","maria@gmail.com");
        User user2 = new User("2","Alex","123456","alex@gmail.com");
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(user1,user2));
        return ResponseEntity.ok().body(list);
    }
}
