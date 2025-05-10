package com.gabrielnz.workshopmongodb.controller;

import com.gabrielnz.workshopmongodb.domain.Roles;
import com.gabrielnz.workshopmongodb.domain.User;
import com.gabrielnz.workshopmongodb.dto.LoginDTO;

import com.gabrielnz.workshopmongodb.dto.UserDTO;
import com.gabrielnz.workshopmongodb.repository.UserRepository;
import com.gabrielnz.workshopmongodb.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;

    public ResponseEntity<String> login(LoginDTO user) {
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        return ResponseEntity.ok(tokenService.createToken((User)auth.getPrincipal()));
    }

    public ResponseEntity<String> register(UserDTO user) {
        if(userRepository.findByName(user.getName()) != null) return ResponseEntity.badRequest().body("Something went wrong");
        User newUser = new User(null,user.getName(),new BCryptPasswordEncoder().encode(user.getPassword()),user.getEmail(), Roles.USER);
        userRepository.save(newUser);
        return ResponseEntity.ok().body("User registered successfully");
    }
}
