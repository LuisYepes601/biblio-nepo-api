/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Idioms;

import com.nexus.biblioNepo.DTOS.response.Idioms.IdiomRespDto;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.Idiomas.IIdiomasService;
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
        name = "Idiomas",
        description = "Módulo encargado de operaiones que no requieren permisos sobre lso idiomas")
@RequestMapping(value = "/api/v1/idiomas")
@RestController
public class IdiomasControllerService {

    private IIdiomasService idiomasService;

    @Autowired
    public IdiomasControllerService(IIdiomasService idiomasService) {
        this.idiomasService = idiomasService;
    }

    @Operation(description = "Operación enccargada de mostrar los idiomas el sistema",
            method = "GET")
    @GetMapping
    public ResponseEntity<PageResponse<IdiomRespDto>> getAll(
            @RequestParam(
                    name = "nombre",
                    required = false) String nombre,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(idiomasService.getAll(nombre, pageable));
    }

}
