/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.RecuperarCredenciales;

import com.nexus.biblioNepo.SERVICES.RecuperacionCrednencil.RecupperacionDeCredenciales;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(name = "Recuperar credenciales",
        description = "Módulo encargado de recuperar credenciales ")
@RequestMapping(value = "/api/v1/recuperar-credenciales")
@RestController
public class RecuperarCredencialesController {
    
    private RecupperacionDeCredenciales recupperacionDeCredenciales;
    
    @Autowired
    public RecuperarCredencialesController(RecupperacionDeCredenciales recupperacionDeCredenciales) {
        this.recupperacionDeCredenciales = recupperacionDeCredenciales;
    }
    
    @Operation(description = "Operación encargada de recuperar contraseña a travez de el email de la aucenta registrada",
            method = "GET")
    @GetMapping()
    public ResponseEntity<BasicResponseDto> recuperarContraseniaByEmail(@RequestParam(name = "email",
            required = true) String email) {
        
        recupperacionDeCredenciales.recuperarContraseñaByEmail(email);
        
        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Se le fue asigna una contraseñna nueva encuentrela en el correo que nos otorgo: " + email));
    }
    
}
