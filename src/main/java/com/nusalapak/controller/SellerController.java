package com.nusalapak.controller;

import com.nusalapak.dto.request.CreateSellerRequest;
import com.nusalapak.dto.response.ResponseTemplate;
import com.nusalapak.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping(value = "/sellers/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseTemplate<?>> register(@RequestBody CreateSellerRequest request) {

        sellerService.create(request);

        ResponseTemplate<?> response = ResponseTemplate.builder()
                .code(HttpStatus.CREATED.value())
                .status("Success")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
