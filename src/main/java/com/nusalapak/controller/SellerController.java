package com.nusalapak.controller;

import com.nusalapak.dto.request.CreateSellerRequest;
import com.nusalapak.dto.request.ProductCreateRequest;
import com.nusalapak.dto.response.ProductCreateResponse;
import com.nusalapak.dto.response.ResponseTemplate;
import com.nusalapak.service.ProductService;
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
@RequestMapping(path = "/api/v1/seller")
public class SellerController {

    private final SellerService sellerService;
    private final ProductService productService;


    @PostMapping(value = "/register",
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

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<?>> addProduct(@RequestBody ProductCreateRequest request) {

        ProductCreateResponse productResponse = productService.addProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseTemplate.builder()
                        .code(HttpStatus.CREATED.value())
                        .message("Success")
                        .data(productResponse)
                        .build());
    }

}
