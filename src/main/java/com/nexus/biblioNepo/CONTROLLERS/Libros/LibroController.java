/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Libros;

import com.nexus.biblioNepo.DTOS.response.Libro.DetalleLibroBasicRespDto;
import com.nexus.biblioNepo.DTOS.response.Libro.LibroBasicDtoResonse;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.Libros.ILibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luis
 */
@Tag(name = "Libros",
        description = "Módulo encargado de gestionar las distintas operaciones que no requieran permisos sobre los libros del "
        + "sistema")
@RequestMapping(value = "/api/v1/libros")
@RestController
public class LibroController {

    private ILibroService libroService;

    @Autowired
    public LibroController(ILibroService libroService) {
        this.libroService = libroService;
    }

    @Operation(description = "Operación encragda de mostrar libros en el sistema segun filtros",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<LibroBasicDtoResonse>> getAll(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "id_lib", required = false) Long id_lib,
            @RequestParam(value = "excluyed_by_id", required = false) Long excluyed_by_id,
            @RequestParam(value = "nombre_autor", required = false) String nombre_autor,
            @RequestParam(value = "id_autor", required = false) Integer id_autor,
            @RequestParam(value = "id_cat", required = false) Integer id_cat,
            @RequestParam(value = "id_genero", required = false) Integer id_genero,
            @RequestParam(value = "id_idiom", required = false) Integer id_idiom,
            @RequestParam(value = "id_tipo_libro", required = false) Integer id_tipo_libro,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(libroService.getAll(id_lib, excluyed_by_id, nombre_autor, id_autor, id_cat,
                        id_genero, id_idiom, id_tipo_libro, pageable));

    }

    @Operation(description = "Operación encargada de mostras los detalles basicos de un libro")
    @GetMapping(value = "/{id}/details")
    public ResponseEntity<DetalleLibroBasicRespDto> getDetailsById(@PathVariable(name = "id", required = true) Long id) {

        return ResponseEntity
                .ok()
                .body(libroService.getDetailsByID(id));
    }

}
