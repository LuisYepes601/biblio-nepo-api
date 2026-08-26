/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Formatolibro;

import com.nexus.biblioNepo.DTOS.request.Formatolibro.FormatolibroDtoReq;
import com.nexus.biblioNepo.SERVICES.Formatolibro.IFormatoLibroAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        name = "Admistrar Formatos de libros",
        description = "Módulo encargado de administrar los formatos de libros del sistema")
@RequestMapping(value = "/api/v1/formato-libros/admin")
@RestController
public class FormatoLibroAdminController {

    private IFormatoLibroAdminService formatoLibroAdminService;

    @Autowired
    public FormatoLibroAdminController(IFormatoLibroAdminService formatoLibroAdminService) {
        this.formatoLibroAdminService = formatoLibroAdminService;
    }

    @Operation(description = "Operación encargada de crear formatos de libros en el sistema",
            method = "POST")
    @PostMapping
    public ResponseEntity<BasicResponseDto> create(
            @Valid
            @RequestBody(required = true) FormatolibroDtoReq formatolibroDtoReq) {

        formatoLibroAdminService.create(formatolibroDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El formato de libro ha sido creado con éxito al sistema"));
    }

    @Operation(description = "Operación encargada de actualizar informacion de los formtaos de libros del sistema",
            method = "PUT")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> updateByID(
            @PathVariable(
                    name = "id",
                    required = true) Integer id,
            @RequestBody(required = true) FormatolibroDtoReq formatolibroDtoReq) {

        formatoLibroAdminService.update(id, formatolibroDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El formato del libro ha sido actualizado con exito en el sistema"));
    }

    @Operation(description = "Operación encargada de eliminar un formato de libro del sistema",
            method = "DELETE")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> deleteByID(@PathVariable(
            name = "id",
            required = true) Integer id) {

        formatoLibroAdminService.deleteById(id);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El formtao de libro ha sido eliminado con éxito del sistema"));
    }
}
