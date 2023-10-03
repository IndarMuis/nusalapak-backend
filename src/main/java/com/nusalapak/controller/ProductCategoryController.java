package com.nusalapak.controller;

import com.nusalapak.dto.response.ProductCategoryResponse;
import com.nusalapak.dto.response.ResponseTemplate;
import com.nusalapak.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/product-category")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("")
    public ResponseEntity<ResponseTemplate<?>> findAllCategory() {
        List<ProductCategoryResponse> categories = productCategoryService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseTemplate.builder()
                        .code(HttpStatus.OK.value())
                        .message("Success")
                        .data(categories).build());
    }

}
