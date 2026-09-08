/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Auth;

import com.nexus.biblioNepo.DTOS.request.Auth.AuthDtoRequest;
import com.nexus.biblioNepo.DTOS.response.Auth.AuthResponseDto;
import com.nexus.biblioNepo.SERVICES.Auth.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luis
 */
@RequestMapping(value = "/api/v1/auth")
@RestController
public class AuthController {

    private IAuthService iAuthService;

    @Autowired
    public AuthController(IAuthService iAuthService) {
        this.iAuthService = iAuthService;
    }

    @Operation(description = "Operación encargada de autenticarme o iniicar sesion en el sistema",
            method = "POST")
    @PostMapping()
    public ResponseEntity<AuthResponseDto> auntenticarse(
            @Valid
            @RequestBody(required = true) AuthDtoRequest authDtoRequest) {

        return ResponseEntity
                .ok()
                .body(iAuthService.autenticarse(authDtoRequest));

    }

}
