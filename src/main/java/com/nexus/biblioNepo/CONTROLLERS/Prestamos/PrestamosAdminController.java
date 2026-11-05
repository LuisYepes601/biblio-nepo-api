/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Prestamos;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Prestamos.PrestamosAdminDtoResp;
import com.nexus.biblioNepo.SERVICES.Prestamos.IPrestamosAdminService;
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
        name = "Administrador de Prestamos",
        description = "Módulo encargado de gestionar las distintas operaciones de los prestamos del sistema.")
@RequestMapping(value = "/api/v1/prestamos/admin")
@RestController
public class PrestamosAdminController {

    private IPrestamosAdminService prestamosAdminService;

    @Autowired
    public PrestamosAdminController(IPrestamosAdminService prestamosAdminService) {
        this.prestamosAdminService = prestamosAdminService;
    }

    @Operation(description = "Operación encargada de mostrar los prestamos del sistema",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<PrestamosAdminDtoResp>> getPrestamos(
            @RequestParam(name = "email", required = false) String email,
            Pageable pageable
    ) {
        return ResponseEntity
                .ok()
                .body(prestamosAdminService.getPrestamos(email, pageable));

    }

}
