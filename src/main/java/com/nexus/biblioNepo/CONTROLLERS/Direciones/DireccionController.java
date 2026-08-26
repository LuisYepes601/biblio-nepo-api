/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Direciones;

import com.nexus.biblioNepo.DTOS.request.DireccionDtoReq;
import com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionBasicDto;
import com.nexus.biblioNepo.SERVICES.Direcciones.IDireccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(
        name = "Direcciones",
        description = "Módulo encargado de distintas operaciones de las direcciones del sistema")
@RequestMapping(value = "/api/v1/direcciones")
@RestController
public class DireccionController {

    private IDireccionService direccionService;

    @Autowired
    public DireccionController(IDireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @Operation(description = "Operación encargada de crerar una direccion en el sistema",
            method = "POST")
    @PostMapping()
    public ResponseEntity<BasicResponseDto> create(@Valid
            @RequestBody DireccionDtoReq direccionDtoReq) {

        direccionService.create(direccionDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("La dirección ha sido agregado con exito al sistema"));
    }

    @Operation(description = "Operación encargada de actualizar datos de una dirección",
            method = "UPDATE")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> updateById(
            @PathVariable(
                    name = "id",
                    required = true) Integer id,
            @Valid
            @RequestBody(required = true) DireccionDtoReq direccionDtoReq) {

        direccionService.updateById(id, direccionDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("La dirección ha sido actualizada con éxito"));
    }

    @Operation(description = "Operación encargada de mostrar la direccion de un uusrio por su id",
            method = "GET")
    @GetMapping(value = "/{id}/user")
    public ResponseEntity<DireccionBasicDto> getByIdUser(
            @PathVariable(
                    name = "id",
                    required = true) Integer id) {

        return ResponseEntity
                .ok()
                .body(direccionService.getByIdUser(id));
    }

}
