package com.nusalapak.controller;

import com.nusalapak.dto.request.CreateOrderRequest;
import com.nusalapak.dto.response.WebResponse;
import com.nusalapak.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Order Product Resources")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<WebResponse<?>> create(@RequestBody CreateOrderRequest request) {
        orderService.create(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("OK")
                        .build());
    }

}
