/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Ciudad;

import com.nexus.biblioNepo.DTOS.response.City.CityDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.Ciudad.ICiudadService;
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
@Tag(name = "Ciudades",
        description = "Módulo encaragado de gestionar las operaciones que no requiern permiso sosbre las ciudades del sitema")
@RequestMapping(value = "/api/v1/ciudades")
@RestController
public class CiudadController {

    private ICiudadService ciudadService;

    @Autowired
    public CiudadController(ICiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @Operation(description = "Operación encargada de mostrar las ciudades del sistema")
    @GetMapping
    public ResponseEntity<PageResponse<CityDtoResp>> getAll(
            @RequestParam(
                    name = "nombre",
                    required = false) String nombre,
            @RequestParam(
                    name = "id_dep",
                    required = false) Integer id_dep,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(ciudadService.getAll(nombre, id_dep, pageable));
                
    }

}
