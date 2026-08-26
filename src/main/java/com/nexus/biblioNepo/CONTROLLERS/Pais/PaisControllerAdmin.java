/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Pais;

import com.nexus.biblioNepo.DTOS.request.Pais.PaisDtoReq;
import com.nexus.biblioNepo.SERVICES.Pais.IPaisServiceAdmin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(name = "Administrar Paises",
        description = "Módulo encaragado de administrar paises en el sistema")
@RequestMapping(value = "/api/v1/paises/admin")
@RestController
public class PaisControllerAdmin {

    private IPaisServiceAdmin paisServiceAdmin;

    @Autowired
    public PaisControllerAdmin(IPaisServiceAdmin paisServiceAdmin) {
        this.paisServiceAdmin = paisServiceAdmin;
    }

    @Operation(description = "Operación encaragda de crear paises en el sitema")
    @PostMapping()
    public ResponseEntity<BasicResponseDto> creat(@Valid
            @RequestBody PaisDtoReq paisDtoReq) {

        paisServiceAdmin.create(paisDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El pais ha sido creado con exito en el sistema"));
    }

}
