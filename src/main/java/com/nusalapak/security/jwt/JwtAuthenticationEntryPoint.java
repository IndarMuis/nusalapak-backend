package com.nusalapak.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusalapak.dto.response.ResponseTemplate;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseTemplate<?> webResponse = ResponseTemplate.builder()
                .code(HttpServletResponse.SC_FORBIDDEN)
                .message("unauthorized")
                .build();
        String responseJson = objectMapper.writeValueAsString(webResponse);

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseJson);
    }
}
