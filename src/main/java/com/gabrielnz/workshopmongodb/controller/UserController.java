package com.gabrielnz.workshopmongodb.controller;

import com.gabrielnz.workshopmongodb.domain.User;
import com.gabrielnz.workshopmongodb.dto.UserDTO;
import com.gabrielnz.workshopmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = userService.findAll();
        List<UserDTO> userDTOList = list.stream().map(x -> new UserDTO(x)).toList();
        return ResponseEntity.ok().body(userDTOList);
    }
}
