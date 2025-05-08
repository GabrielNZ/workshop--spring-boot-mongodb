package com.gabrielnz.workshopmongodb.controller.exception;

import com.gabrielnz.workshopmongodb.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ObjectNotFoundException.class)
    public StandartException objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        return new StandartException(Instant.now(), HttpStatus.NOT_FOUND.value(),"Resource not found", e.getMessage(), request.getRequestURI());
    }
}
