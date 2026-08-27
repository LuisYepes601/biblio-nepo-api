/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.TipoLibro;

import com.nexus.biblioNepo.DTOS.request.TipoLibro.TipoLibroDtoReq;
import com.nexus.biblioNepo.SERVICES.TipoLibro.ITipoLibroAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(name = "Administración de Tipos de Libro",
        description = "Módulo encaragada de administrar distintas "
        + "operacions rlacionadas a los tipos de libros del sistema.")
@RequestMapping(value = "/api/v1/tipo-libros/admin")
@RestController
public class TipoLibroControllerAdmin {

    private ITipoLibroAdminService tipoLibroAdminService;

    @Autowired
    public TipoLibroControllerAdmin(ITipoLibroAdminService tipoLibroAdminService) {
        this.tipoLibroAdminService = tipoLibroAdminService;
    }

    @Operation(description = "Operación encaragda de crear un tipo de libro al sistema",
            method = "POST")
    @PostMapping()
    public ResponseEntity<BasicResponseDto> create(
            @Valid
            @RequestBody(required = true) TipoLibroDtoReq tipoLibroDtoReq) {

        tipoLibroAdminService.create(tipoLibroDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El tipo d elibro ha sido agregado a el sistema"));

    }

}
