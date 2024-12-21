package com.fixsys.ctfyphcd.fiter;

import com.fixsys.ctfyphcd.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String path = request.getRequestURI();

        // Ignorar los endpoints públicos como login
        if (path.equals("/api/v1/auth/profesional/login") || path.equals("/api/v1/auth/admin/login")) {
            chain.doFilter(request, response); // Continua con la cadena sin procesar el filtro
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");

        String dni = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            dni = jwtUtil.extractDni(jwt);
        }

        if (dni != null && jwtUtil.validateToken(jwt, dni)) {
            Claims claims = jwtUtil.extractAllClaims(jwt);
            List<String> roles = (List<String>) claims.get("roles");

            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(dni, null, authorities);

            System.out.println("Authorities asignadas: " + authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            if (jwt != null || authorizationHeader != null) {
                System.out.println("JWT inválido o no presente");
            }
        }

        chain.doFilter(request, response);
    }

}
