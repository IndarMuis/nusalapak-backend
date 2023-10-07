package com.nusalapak.controller;

import com.nusalapak.dto.response.WebResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponse<?>> constraintViolationException(ConstraintViolationException exc) {
        WebResponse<?> response = WebResponse.builder()
                .message("BAD REQUEST")
                .code(HttpStatus.BAD_REQUEST.value())
                .errors(exc.getMessage()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<?>> responseStatusException(ResponseStatusException exc) {
        WebResponse<?> response = WebResponse.builder()
                .message(getErrorMessage(exc.getStatusCode().value()))
                .code(exc.getStatusCode().value())
                .errors(exc.getReason()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WebResponse<?>> errorException(Exception exc) {
        WebResponse<?> response = WebResponse.builder()
                .message("INTERNAL SERVER ERROR")
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errors(exc.getMessage()).build();
        return ResponseEntity.internalServerError().body(response);
    }

    private String getErrorMessage(int code) {
        if (code == 404) {
            return "DATA NOT FOUND";
        } else if (code == 400) {
            return "BAD REQUEST";
        } else if (code == 401) {
            return "UNAUTHORIZED";
        } else if (code == 403) {
            return "FORBIDDEN";
        } else {
            return "INTERNAL SERVER ERROR";
        }
    }

}