/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Categoria;

import com.nexus.biblioNepo.DTOS.request.Categoria.CategoriaAsignarDto;
import com.nexus.biblioNepo.DTOS.response.Categoria.CategoriaDtoresp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.Categoria.ICategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Operation(description = "Operación encaragda de asignarles ccategorias a un libro",
            method = "POST")
    @PostMapping(value = "/asignar-by-libro/{id_libro}")
    public ResponseEntity<BasicResponseDto> asignarCaegoriasALibro(
            @PathVariable(
                    name = "id_libro",
                    required = true) Long id_libro,
        
            @RequestBody(required = true) List<@Valid CategoriaAsignarDto> categoriasAsignarDtos) {

        categoriaService.asignarCategoriaLibro(id_libro, categoriasAsignarDtos);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Las catgeorias han sido añaidas al libro con éxito"));
    }

}
