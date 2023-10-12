package com.nusalapak.controller;

import com.nusalapak.dto.response.ProductCategoryResponse;
import com.nusalapak.dto.response.WebResponse;
import com.nusalapak.service.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/product-categories")
@Tag(name = "Product Category Resources")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @Operation(
            description = "Get Public Endpoint",
            summary = "Get all product category",
            responses = @ApiResponse(
                    description = "OK",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
//                                    schema = @Schema(implementation = ProductCategoryResponse.class)
                                    array = @ArraySchema(schema = @Schema(implementation = ProductCategoryResponse.class))
                            )
                    }
            )
    )
    @GetMapping("")
    public ResponseEntity<WebResponse<?>> findAllCategory() {
        List<ProductCategoryResponse> categories = productCategoryService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("OK")
                        .data(categories).build());
    }

}
