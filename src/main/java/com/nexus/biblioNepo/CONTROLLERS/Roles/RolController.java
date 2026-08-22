/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Roles;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDtoBasic;
import com.nexus.biblioNepo.SERVICES.Roles.IRolService;
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
@Tag(
        name = "Rol Basic",
        description = "Módulo encargado de gestionar datos basicos de roles del sistema")
@RequestMapping(value = "/api/v1/roles")
@RestController
public class RolController {

    private IRolService rolService;

    @Autowired
    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }

    @Operation(description = "Operación encargada de mostrar roles con informacion basica")
    @GetMapping()
    public ResponseEntity<PageResponse<RolDtoBasic>> getAll(Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(rolService.getAll(pageable));
    }

}
