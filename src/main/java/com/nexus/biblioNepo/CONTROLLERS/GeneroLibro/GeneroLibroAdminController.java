/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.GeneroLibro;

import com.nexus.biblioNepo.DTOS.request.GeneroLibro.GenerolibroDtoReq;
import com.nexus.biblioNepo.DTOS.response.GenerLibro.GeneroLibroAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.GenerLibro.GeneroLibroDetailsAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.GeneroLibre.IGeneroLibroServiceAdmin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Operation(description = "Operación encargada de mostrar todos los generos que se encuentran en el sistema",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<GeneroLibroAdminDtoResp>> getAll(
            @RequestParam(
                    name = "nombre",
                    required = false) String nombre,
            @RequestParam(
                    name = "isDelete",
                    required = false) Boolean isDelete,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(generoLibroServiceAdmin.getAll(nombre, isDelete, pageable));

    }

    @Operation(description = "Operación encargada de mostrar los detallles de un genero del sistema",
            method = "GET")
    @GetMapping(value = "/{id}/details")
    public ResponseEntity<GeneroLibroDetailsAdminDtoResp> getDetailsByID(
            @PathVariable(name = "id", required = true) Integer id) {

        return ResponseEntity
                .ok()
                .body(generoLibroServiceAdmin.getDetailsById(id));
    }

}
