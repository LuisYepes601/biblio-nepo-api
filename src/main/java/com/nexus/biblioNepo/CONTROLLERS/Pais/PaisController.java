/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Pais;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Pais.PaisDtoResp;
import com.nexus.biblioNepo.SERVICES.Pais.IPaisservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luis
 */
@Tag(name = "Paises",
        description = "Módulo encaragdo de hacer operaciones que no soliciten permisos")
@RequestMapping(value = "/api/v1/paises")
@RestController
public class PaisController {

    private IPaisservice paisservice;

    @Autowired
    public PaisController(IPaisservice paisservice) {
        this.paisservice = paisservice;
    }

    @Operation(
            description = "Operación encargada de mostras los paises disponibles en el sistema",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<PaisDtoResp>> getAll(Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(paisservice.getAll(pageable));
    }

}
