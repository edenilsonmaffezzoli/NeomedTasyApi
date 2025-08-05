package com.NeomedTasyApi.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
    
    private static final String REQUIRED_TOKEN = "r5b6gVwpy8M41WLh7y1Q5ao5MUQYi2398Bltak3h7lxHXX8HuyetAV81reKzwR8E";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        
        if (token == null || !token.equals(REQUIRED_TOKEN)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido ou não fornecido");
            return false;
        }
        
        return true;
    }
}