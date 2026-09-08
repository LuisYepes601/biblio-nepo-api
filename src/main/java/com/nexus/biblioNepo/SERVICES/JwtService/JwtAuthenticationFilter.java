/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.JwtService;

import com.nexus.biblioNepo.SERVICES.UserDetails.CustomerUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author luis
 */
@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomerUserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, CustomerUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Obtener el header Authorization
        final String authHeader
                = request.getHeader("Authorization");

        // 2. Si no existe o no comienza con Bearer,
        // dejamos continuar la petición
        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extraer solamente el token
        String token = authHeader.substring(7);

        String email;

        try {

            // 4. Extraer el email del JWT
            email = jwtService.extractUsername(token);

        } catch (Exception e) {

            // Token inválido
            filterChain.doFilter(request, response);
            return;
        }

        // 5. Verificar que todavía no exista
        // una autenticación en el contexto
        if (email != null
                && SecurityContextHolder
                        .getContext()
                        .getAuthentication() == null) {

            // 6. Buscar el usuario en PostgreSQL
            UserDetails userDetails
                    = userDetailsService
                            .loadUserByUsername(email);

            // 7. Validar el JWT
            if (jwtService.isTokenValid(
                    token,
                    userDetails)) {

                // 8. Crear la autenticación
                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 9. Agregar detalles de la petición
                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // 10. Guardar la autenticación
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        }

        // 11. Continuar con la petición
        filterChain.doFilter(request, response);
    }
}
