package com.gabrielnz.workshopmongodb.controller.exception;

import com.gabrielnz.workshopmongodb.service.exception.DataBaseIntegrity;
import com.gabrielnz.workshopmongodb.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartException> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandartException(Instant.now(), HttpStatus.NOT_FOUND.value(),"Resource not found", e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(DataBaseIntegrity.class)
    public ResponseEntity<StandartException> objectNotFound(DataBaseIntegrity e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandartException(Instant.now(), HttpStatus.BAD_REQUEST.value(),"DataBase bad request", e.getMessage(), request.getRequestURI()));
    }
}
