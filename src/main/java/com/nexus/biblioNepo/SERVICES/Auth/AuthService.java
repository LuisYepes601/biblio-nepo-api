/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Auth;

import com.nexus.biblioNepo.DTOS.request.Auth.AuthDtoRequest;
import com.nexus.biblioNepo.DTOS.response.Auth.AuthResponseDto;
import com.nexus.biblioNepo.ENTYTIES.usuario;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import com.nexus.biblioNepo.SERVICES.JwtService.JwtService;
import com.nexus.biblioNepo.SERVICES.UserDetails.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author luis
 */
@Service
public class AuthService implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomerUserDetailsService userDetailsService;
    private final usuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, CustomerUserDetailsService userDetailsService, usuarioRepository usuarioRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponseDto autenticarse(AuthDtoRequest authDtoRequest) {

        // 1. Verificar email y contraseña
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDtoRequest.getEmail(),
                        authDtoRequest.getPassword()
                )
        );

        // 2. Buscar los datos del usuario
        usuario usuario = usuarioRepository
                .findByEmail(authDtoRequest.getEmail().trim())
                .orElseThrow(()
                        -> new RuntimeException("Usuario no encontrado")
                );

        // 3. Obtener UserDetails
        UserDetails userDetails
                = userDetailsService.loadUserByUsername(
                        authDtoRequest.getEmail()
                );

        // 4. Generar JWT
        String token = jwtService.generateToken(
                userDetails,
                usuario.getId()
        );

        // 5. Devolver respuesta
        return new AuthResponseDto(token);
    }

}
