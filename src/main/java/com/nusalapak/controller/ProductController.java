package com.nusalapak.controller;

import com.nusalapak.dto.request.ProductCreateRequest;
import com.nusalapak.dto.response.ProductCreateResponse;
import com.nusalapak.dto.response.ResponseTemplate;
import com.nusalapak.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ResponseTemplate<?>> add(@RequestBody ProductCreateRequest request) {

        ProductCreateResponse productResponse = productService.addProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseTemplate.builder()
                        .code(HttpStatus.CREATED.value())
                        .message("Success")
                        .data(productResponse)
                        .build());
    }

}
