/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.TipoLibro;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.TipoLibro.TipoLibroDtoResp;
import com.nexus.biblioNepo.SERVICES.TipoLibro.ITipoLibroService;
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
@Tag(name = "Tipo de Libros",
        description = "Módulo encargado de gestionar operaciones que no requieren permisos, con relacion a los tipos de libros"
        + " del sistema")
@RequestMapping(value = "/api/v1/tipo-libros")
@RestController
public class TipoLibroController {

    private ITipoLibroService tipoLibroService;

    @Autowired
    public TipoLibroController(ITipoLibroService tipoLibroService) {
        this.tipoLibroService = tipoLibroService;
    }

    @Operation(description = "Operación encaragada de mostrar los tipos de libros del istema que estan activos",
            method = "GET")
    @GetMapping
    public ResponseEntity<PageResponse<TipoLibroDtoResp>> getAll(
            @RequestParam(
                    name = "nombre",
                    required = false) String nombre,
            Pageable pageable
    ) {

        return ResponseEntity
                .ok()
                .body(tipoLibroService.getAll(pageable, nombre));
    }

}
