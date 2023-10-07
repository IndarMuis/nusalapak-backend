package com.nusalapak.controller;

import com.nusalapak.dto.request.ProductCreateRequest;
import com.nusalapak.dto.response.ProductCreateResponse;
import com.nusalapak.dto.response.ProductResponse;
import com.nusalapak.dto.response.WebResponse;
import com.nusalapak.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<WebResponse<?>> add(@RequestBody ProductCreateRequest request) {

        log.info("REQUEST ", request);
        ProductCreateResponse productResponse = productService.addProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(WebResponse.builder()
                        .code(HttpStatus.CREATED.value())
                        .message("CREATED")
                        .data(productResponse)
                        .build());
    }

    @GetMapping("")
    public ResponseEntity<WebResponse<?>> findAll() {
        List<ProductResponse> products = productService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(
                WebResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("OK")
                        .data(products).build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebResponse<?>> findById(@PathVariable("id") UUID id) {
        ProductResponse productResponse = productService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                WebResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("OK")
                        .data(productResponse).build()
        );
    }

}
