package com.nusalapak.controller;

import com.nusalapak.dto.request.LoginRequest;
import com.nusalapak.dto.response.AccountLoginResponse;
import com.nusalapak.dto.response.WebResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<WebResponse<?>> login(@RequestBody LoginRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid email or password");
        }

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AccountLoginResponse response = AccountLoginResponse.builder()
                .accessToken(jwtService.generateToken(authentication)).build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(WebResponse.builder()
                        .code(HttpStatus.OK.value())
                        .status("OK")
                        .data(response).build()
                );

    }
}
