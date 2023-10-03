package com.nusalapak.controller;

import com.nusalapak.dto.response.ResponseTemplate;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseTemplate<?>> constraintViolationException(ConstraintViolationException exc) {
        ResponseTemplate<?> response = ResponseTemplate.builder()
                .message("Error")
                .code(HttpStatus.BAD_REQUEST.value())
                .errors(exc.getMessage()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseTemplate<?>> responseStatusException(ResponseStatusException exc) {
        ResponseTemplate<?> response = ResponseTemplate.builder()
                .message("Error")
                .code(exc.getStatusCode().value())
                .errors(exc.getReason()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseTemplate<?>> errorException(Exception exc) {
        ResponseTemplate<?> response = ResponseTemplate.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errors(exc.getMessage()).build();
        return ResponseEntity.internalServerError().body(response);
    }

}
