/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Departamentos;

import com.nexus.biblioNepo.DTOS.response.Departamentos.DepartamentoBasicDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.Departamentos.IDepartamentoService;
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
@Tag(name = "Departamentos",
        description = "Módulo encaragdo de opereaciones sobre los departamentos sin permisnos soliciatdos")
@RequestMapping(value = "/api/v1/departamentos")
@RestController
public class DepartamentoController {

    private IDepartamentoService departamentoService;

    @Autowired
    public DepartamentoController(IDepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @Operation(
            description = "Operación encargada de mostrar los departamentos del sitema",
            method = "GET")
    @GetMapping
    public ResponseEntity<PageResponse<DepartamentoBasicDtoResp>> getAll(
            @RequestParam(
                    name = "nombre",
                    required = false) String nombre,
            @RequestParam(
                    name = "id_pais",
                    required = false) Integer id_pais,
            Pageable pageable
    ) {

        return ResponseEntity
                .ok()
                .body(departamentoService.getAll(nombre, id_pais, pageable));
    }

}
