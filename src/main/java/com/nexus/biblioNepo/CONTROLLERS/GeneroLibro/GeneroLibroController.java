/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.GeneroLibro;

import com.nexus.biblioNepo.DTOS.request.GeneroLibro.GeneroDtoBasicReq;
import com.nexus.biblioNepo.DTOS.response.GenerLibro.GeneroLibroDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.GeneroLibre.IGeneroLibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(name = "Genero de Libros",
        description = "Módulo encargado de gestionar distintas operaciones relacionadas a los generos que no"
        + "requieren permisos especiales")
@RequestMapping(value = "/api/v1/genero-libros")
@RestController
public class GeneroLibroController {

    private IGeneroLibroService generoLibroService;

    @Autowired
    public GeneroLibroController(IGeneroLibroService generoLibroService) {
        this.generoLibroService = generoLibroService;
    }

    @Operation(description = "Operación encargada de mostras los generos activos del sistema",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<GeneroLibroDtoResp>> getAll(
            @RequestParam(
                    name = "nombre",
                    required = false) String nombre,
            Pageable pageable
    ) {

        return ResponseEntity
                .ok()
                .body(generoLibroService.getAll(nombre, pageable));
    }

    @Operation(description = "Operación encragada de asignar generos a un libro ya existente",
            method = "POST")
    @PostMapping(value = "/asignar")
    public ResponseEntity<BasicResponseDto> asignarGEnerosALibro(
            @RequestParam(name = "id_libro",
                    required = true) Long id_libro,
            @RequestBody(required = true) List<@Valid GeneroDtoBasicReq> generos
    ) {

        generoLibroService.asignarGenerosToLibro(id_libro, generos);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Se han asignado con exito los generos al libro"));
    }

}
