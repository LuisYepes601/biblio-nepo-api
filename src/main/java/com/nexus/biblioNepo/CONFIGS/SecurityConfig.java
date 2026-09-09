/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONFIGS;

import com.nexus.biblioNepo.SERVICES.JwtService.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author luis
 */
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // Desactivamos CSRF porque nuestra API utilizará JWT
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                // No utilizaremos sesiones
                .sessionManagement(session
                        -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )
                )
                // Configuración de endpoints
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                // Login público
                .requestMatchers("/api/v1/auth", "/api/v1/auth/**")
                .permitAll()
                //register 
                .requestMatchers("/api/v1/register")
                .permitAll()
                //rcueperacion de credenciales
                .requestMatchers("/api/v1/recuperar-credenciales")
                .permitAll()
                //categorias de libro
                .requestMatchers("/api/v1/categoria-libros")
                .permitAll()
                // Swagger público
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()
                //libros
                .requestMatchers("/api/v1/libros/admin")
                .permitAll()
                .requestMatchers("api/v1/libros/{id_libro}/details")
                .permitAll()
                //idomas
                .requestMatchers("/api/v1/idiomas")
                .permitAll()
                //tipolibros
                .requestMatchers("/api/v1/tipo-libros")
                .permitAll()
                //idiomas
                .requestMatchers("/api/v1/idiomas")
                .permitAll()
                //genero libro
                .requestMatchers("/api/v1/genero-libros")
                .permitAll()
                //tipo identificaciones
                .requestMatchers("/api/v1/admin/tipo-identificaciones")
                .permitAll()
                //roles
                .requestMatchers("/api/v1/roles")
                .permitAll()
                // Todo lo demás necesita autenticación
                .anyRequest().authenticated()
                )
                // Nuestro filtro JWT se ejecuta antes del filtro
                // de autenticación de usuario y contraseña
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
}
