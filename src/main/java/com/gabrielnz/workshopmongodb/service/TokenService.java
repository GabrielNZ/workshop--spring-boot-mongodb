package com.gabrielnz.workshopmongodb.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gabrielnz.workshopmongodb.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${JWT_SECRET}")
    private String secret;

   public String createToken(User user) {
       try{
       Algorithm algorithm = Algorithm.HMAC256(secret);
       return JWT.create().withIssuer("API_JWT_TOKEN").withSubject(user.getUsername()).withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC)).sign(algorithm);
       }catch (JWTVerificationException e) {
           return "";
       }
   }
   public String verifyToken(String token) {
       try {
           Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.require(algorithm).withIssuer("API_JWT_TOKEN").build().verify(token).getSubject();
       }catch (JWTVerificationException e) {
           return "";
       }
   }
}
