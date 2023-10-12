package com.nusalapak.controller;

import com.nusalapak.dto.request.CreateAdminRequest;
import com.nusalapak.dto.response.WebResponse;
import com.nusalapak.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v1/admin")
@Tag(name = "Admin Resources")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<WebResponse<?>> register(@RequestBody CreateAdminRequest request) {
        adminService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).
                body(WebResponse.builder()
                        .code(HttpStatus.CREATED.value())
                        .message("CREATED").build()
                );
    }


}
