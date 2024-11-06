package com.genius.rms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        // Custom response body
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", "Forbidden");
        responseBody.put("message", "You do not have permission to access this route.");
        responseBody.put("status", HttpServletResponse.SC_FORBIDDEN);

        // Convert to json
        String jsonResponse = new ObjectMapper().writeValueAsString(responseBody);
        response.getWriter().write(jsonResponse);
    }
}
