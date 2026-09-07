/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Perfil;

import com.nexus.biblioNepo.DTOS.response.Perfil.UsuarioPerfilDtoResp;
import com.nexus.biblioNepo.SERVICES.Perfil.IPerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 *
 */
@Tag(name = "Perfil",
        description = "Módulo encargado de gestionar informacion del perfil del usuario")
@RequestMapping(value = "/api/v1/perfil")
@RestController
public class PerfilController {

    private IPerfilService perfilService;

    @Autowired
    public PerfilController(IPerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @Operation(description = "Operación encaragda de actualizar la foto del perfil de un usuario",
            method = "PUT")
    @PutMapping(value = "/{id_user}/foto-perfil")
    public ResponseEntity<BasicResponseDto> updateFotoPerfil(
            @PathVariable(
                    name = "id_user",
                    required = true) Long id_user,
            @RequestPart(
                    name = "file",
                    required = true) MultipartFile file) {

        perfilService.updateFotoPerfil(id_user, file);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Se ha a ctulizado la foto con exito"));
    }

    @Operation(description = "Operación encargada de mostrar los datos basicos de un perfil de usuario",
            method = "GET")
    @GetMapping(value = "/{id_user}/datos-basicos")
    public ResponseEntity<UsuarioPerfilDtoResp> getDatosBasicosPerfil(
            @PathVariable(
                    name = "id_user",
                    required = true) Long id_user) {

        return ResponseEntity
                .ok()
                .body(perfilService.getDatosBasicos(id_user));
    }
}
