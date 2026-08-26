/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Formatolibro;

import com.nexus.biblioNepo.DTOS.response.FormatoLibro.FormatoLibrODtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.Formatolibro.IFormatoLibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luis
 */
@Tag(name = "Formato de Libros",
        description = "Módulo encargado de las funcionalidades que no requieren permisos "
        + "sobre los formatos de libros del sistema")
@RequestMapping(value = "/api/v1/formato-libros")
@RestController
public class FormatoLibroController {

    private IFormatoLibroService formatoLibroService;

    @Autowired
    public FormatoLibroController(IFormatoLibroService formatoLibroService) {
        this.formatoLibroService = formatoLibroService;
    }

    @Operation(description = "Operación encargada de mostras los formatos activos del sistema con datos basicos",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<FormatoLibrODtoResp>> getAll(
            @RequestParam(
                    name = "nombre",
                    required = false) String nombre,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(formatoLibroService.getAll(nombre, pageable));

    }

}
