/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Register;

import com.nexus.biblioNepo.DTOS.request.Usuarios.UsuarioBasicoDtoReq;
import com.nexus.biblioNepo.SERVICES.Registro.IRegistroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(
        name = "Registro",
        description = "Módulo encaragdo de resgitrar usuarios al sistema")
@RequestMapping(value = "/api/v1/register")
@RestController
public class RegistrerController {

    private IRegistroService registroService;

    @Autowired
    public RegistrerController(IRegistroService registroService) {
        this.registroService = registroService;
    }

    @Operation(description = "Operación encargada de registrar usuarios al sistema")
    @PostMapping()
    public ResponseEntity<BasicResponseDto> register(
            @Valid
            @RequestPart(
                    name = "body",
                    required = true) UsuarioBasicoDtoReq usuarioBasicoDtoReq,
            @RequestPart(
                    name = "imgPerfil",
                    required = false) MultipartFile imgPerfil) {

        registroService.register(usuarioBasicoDtoReq, imgPerfil);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Usuario "
                        + usuarioBasicoDtoReq.getNombre().trim() + " ha sido registrado con éxito."));
    }

}
