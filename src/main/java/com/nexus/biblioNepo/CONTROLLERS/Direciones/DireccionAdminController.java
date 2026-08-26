/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Direciones;

import com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionAdminDtoResp;
import com.nexus.biblioNepo.SERVICES.Direcciones.IDireccionServiceADMIN;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luis
 */
@Tag(name = "Direcciones Admin",
        description = "Módulo encaragado de administrar las direcciones del sistema")
@RequestMapping(value = "/api/v1/direcciones/admin")
@RestController
public class DireccionAdminController {

    private IDireccionServiceADMIN direccionServiceADMIN;

    @Autowired
    public DireccionAdminController(IDireccionServiceADMIN direccionServiceADMIN) {
        this.direccionServiceADMIN = direccionServiceADMIN;
    }

    @Operation(description = "Operación encagrada de mostrar la dirección completa de un usuario",
            method = "GET")
    @GetMapping(value = "/{id_user}")
    public ResponseEntity<DireccionAdminDtoResp> getDirByUserId(
            @PathVariable(
                    name = "id_user",
                    required = true) Integer id) {

        return ResponseEntity
                .ok()
                .body(direccionServiceADMIN.getByIdUser(id));
    }

}
