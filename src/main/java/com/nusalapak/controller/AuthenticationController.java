package com.nusalapak.controller;

import com.nusalapak.dto.request.LoginRequest;
import com.nusalapak.dto.response.LoginResponse;
import com.nusalapak.dto.response.ResponseTemplate;
import com.nusalapak.security.jwt.JwtService;
import com.nusalapak.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<ResponseTemplate<?>> login(@RequestBody LoginRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid email or password");
        }

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginResponse response = LoginResponse.builder()
                .accessToken(jwtService.generateToken(authentication)).build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseTemplate.builder()
                        .code(HttpStatus.OK.value())
                        .status("Success")
                        .data(response).build()
                );

    }
}
