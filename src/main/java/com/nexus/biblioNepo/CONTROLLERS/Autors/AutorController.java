/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Autors;

import com.nexus.biblioNepo.DTOS.response.Autors.AutorDetailsBasciDto;
import com.nexus.biblioNepo.DTOS.response.Autors.AutorDtoBasic;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.Autors.IAutorService;
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
@Tag(
        name = "Autores",
        description = "Módulo que representa las operaciones basicas sin permisos de autores")
@RequestMapping(value = "/api/v1/autors")
@RestController
public class AutorController {

    private IAutorService autorService;

    @Autowired
    public AutorController(IAutorService autorService) {
        this.autorService = autorService;
    }

    @Operation(
            description = "Operación encaragda de mostrar los uatores con datos basicos",
            method = "GET")
    @GetMapping
    public ResponseEntity<PageResponse<AutorDtoBasic>> getAll(
            @RequestParam(
                    name = "name",
                    required = false) String name,
            @RequestParam(
                    name = "id_pais",
                    required = false) Integer id_pais,
            @RequestParam(
                    name = "name_boock",
                    required = false) String name_boock,
            @RequestParam(
                    name = "id_categoria_boock",
                    required = false) Integer id_categoria_boock,
            @RequestParam(
                    name = "excluyed_id",
                    required = false) Integer excluyed_id,
            Pageable pageable
    ) {

        return ResponseEntity
                .ok()
                .body(autorService.getAll(name, id_pais, name_boock, id_categoria_boock, excluyed_id, pageable));
    }

    @Operation(
            description = "Operación encaragda de mostars detalles basicos de un autor",
            method = "GET")
    @GetMapping(value = "/{id}/details")
    public ResponseEntity<AutorDetailsBasciDto> getDetailsById(@PathVariable(name = "id", required = true) Integer id) {

        return ResponseEntity
                .ok()
                .body(autorService.getDetailsBasicByID(id));
    }
}
