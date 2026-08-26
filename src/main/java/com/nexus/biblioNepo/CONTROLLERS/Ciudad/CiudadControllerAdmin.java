/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Ciudad;

import com.nexus.biblioNepo.SERVICES.Ciudad.ICiudadServiceAdmin;
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
@Tag(name = "Adiministracion de Ciudades",
        description = "Módulo encaragda de administrar la información de las ciudades del sistema")
@RequestMapping(value = "/api/v1/ciudades/admin")
@RestController
public class CiudadControllerAdmin {

    private ICiudadServiceAdmin ciudadServiceAdmin;

    @Autowired
    public CiudadControllerAdmin(ICiudadServiceAdmin ciudadServiceAdmin) {
        this.ciudadServiceAdmin = ciudadServiceAdmin;
    }

    @Operation(description = "Operacion encargada de cargar las ciudades al sitema desde la apicolombia")
    @GetMapping()
    public ResponseEntity<BasicResponseDto> cargarCiudades() {

        ciudadServiceAdmin.cargarCiudades();

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Se han cargado con exito las ciudades al sistema"));
    }

}
