/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.GeneroLibro;

import com.nexus.biblioNepo.DTOS.request.GeneroLibro.GenerolibroDtoReq;
import com.nexus.biblioNepo.SERVICES.GeneroLibre.IGeneroLibroServiceAdmin;
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
@Tag(name = "Administración de Generos de libros",
        description = "Módulo encargado de administrar todas las operaciones sobre los generos de libro, del sistema")
@RequestMapping(value = "/api/v1/genero-libros/admin")
@RestController
public class GeneroLibroAdminController {

    private IGeneroLibroServiceAdmin generoLibroServiceAdmin;

    @Autowired
    public GeneroLibroAdminController(IGeneroLibroServiceAdmin generoLibroServiceAdmin) {
        this.generoLibroServiceAdmin = generoLibroServiceAdmin;
    }

    @Operation(description = "Operación encargada de crear generos en el sistema",
            method = "POST")
    @PostMapping()
    public ResponseEntity<BasicResponseDto> create(@Valid
            @RequestBody(required = true) GenerolibroDtoReq generolibroDtoReq) {

        generoLibroServiceAdmin.create(generolibroDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El genero del libro ha sido crado exitosamente en el sistema"));
    }

    @Operation(description = "Operación encargada de actilizar datos de los generos en el sistema",
            method = "UPDATE")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> updateByID(
            @PathVariable(
                    name = "id",
                    required = true) Integer id,
            @Valid
            @RequestBody(required = true) GenerolibroDtoReq generolibroDtoReq) {

        generoLibroServiceAdmin.updateById(id, generolibroDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El genero ha sido actualizado con exito"));

    }

    @Operation(description = "Operación encargada de elimigar un genero del sistema",
            method = "DELETE")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> deleteByID(@PathVariable(name = "id", required = true) Integer id) {

        generoLibroServiceAdmin.deleteByID(id);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El genero ha sido eliminado con éxito del sistema"));
    }

}
