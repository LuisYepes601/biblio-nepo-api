/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Idioms;

import com.nexus.biblioNepo.SERVICES.Idiomas.IIdiomasAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(name = "Admin de Idiomas",
        description = "Módulo encaragado de administrar los idiomas del sistema")
@RequestMapping(value = "/api/v1/idioms/admin")
@RestController
public class IdiomsControllerServiceAdmin {

    private IIdiomasAdminService idiomasAdminService;

    @Autowired
    public IdiomsControllerServiceAdmin(IIdiomasAdminService idiomasAdminService) {
        this.idiomasAdminService = idiomasAdminService;
    }

    @Operation(description = "Operación encargada de cargar idiomas e la api de idiomas",
            method = "GET")
    @GetMapping()
    public ResponseEntity<BasicResponseDto> getIidioms() {

        idiomasAdminService.cargarIdiomas();

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Se han cargado conn éxito los idiomas ala sitema"));
    }

}
