/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Categoria;

import com.nexus.biblioNepo.DTOS.response.Categoria.CategoriaDtoresp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.Categoria.ICategoriaService;
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
@Tag(
        name = "Categorias de libros",
        description = "Módulo encargado de gestionar las operaciones que no necesiten permisos especiales")
@RequestMapping(value = "/api/v1/categoria-libros")
@RestController
public class CategoriaController {

    private ICategoriaService categoriaService;

    @Autowired
    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Operation(description = "Operación encargda de mostrar las categorias activas de los libros",
            method = "GET")
    @GetMapping
    public ResponseEntity<PageResponse<CategoriaDtoresp>> getAll(
            @RequestParam(
                    name = "nombre",
                    required = false) String nombre,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(categoriaService.getAll(nombre, pageable));

    }

}
