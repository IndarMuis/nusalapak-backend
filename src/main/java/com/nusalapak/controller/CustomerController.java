package com.nusalapak.controller;

import com.nusalapak.dto.request.CreateCustomerRequest;
import com.nusalapak.dto.response.WebResponse;
import com.nusalapak.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<WebResponse<?>> register(@RequestBody CreateCustomerRequest request) {
        customerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                WebResponse.builder()
                        .code(HttpStatus.CREATED.value())
                        .message("CREATED").build()
        );
    }

}
