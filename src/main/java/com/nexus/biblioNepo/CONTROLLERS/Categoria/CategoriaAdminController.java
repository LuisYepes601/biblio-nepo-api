/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Categoria;

import com.nexus.biblioNepo.DTOS.request.Categoria.CategoriaDtoReq;
import com.nexus.biblioNepo.SERVICES.Categoria.ICategoriaAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Admin de Categorias",
        description = "Módulo encargado de administrar las operaciones de las categorias de los libros de el sistema")
@RequestMapping(value = "/api/v1/categorias/admin")
@RestController
public class CategoriaAdminController {

    private ICategoriaAdminService categoriaAdminService;

    @Autowired
    public CategoriaAdminController(ICategoriaAdminService categoriaAdminService) {
        this.categoriaAdminService = categoriaAdminService;
    }

    @Operation(description = "Operación encargada de crear categorias de libros en el sistema",
            method = "GET")
    @PostMapping()
    public ResponseEntity<BasicResponseDto> create(@RequestBody(required = true) CategoriaDtoReq categoriaDtoReq) {

        categoriaAdminService.create(categoriaDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("La categoria ha sido creado con exito en el sistema"));
    }

    @Operation(description = "Operación encargada de actulizar datos de la categoria",
            method = "PUT")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> updateById(
            @PathVariable(
                    name = "id",
                    required = true) Integer id,
            @RequestBody(required = true) CategoriaDtoReq categoriaDtoReq) {

        categoriaAdminService.updateById(id, categoriaDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("La categoria ha sido actualizada correctamente en el sistema"));

    }

    @Operation(description = "Operación encargada de eliminaruna categoria del sistema",
            method = "DELETE")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> deleteById(@PathVariable(name = "id", required = true) Integer id) {

        categoriaAdminService.deleteByID(id);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("La categoria ha sido eliminada del sistema"));
    }

}
