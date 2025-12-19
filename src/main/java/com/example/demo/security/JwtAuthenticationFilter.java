package com.example.demo.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);

        if (token != null && jwtUtil.validateToken(token)) {
            String username = jwtUtil.getEmail(token);
            Long userId = jwtUtil.getUserId(token);
            String role = jwtUtil.getRole(token);

            // Set authentication in the context
            CustomUserDetails userDetails = new CustomUserDetails(username, userId, role);
            userDetails.setAuthenticated(true);

            WebAuthenticationDetailsSource detailsSource = new WebAuthenticationDetailsSource();
            userDetails.setDetails(detailsSource.buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(userDetails);
        }

        filterChain.doFilter(request, response);
    }

    // Extract token from Authorization header
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Get token without "Bearer "
        }
        return null;
    }
}
