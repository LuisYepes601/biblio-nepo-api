/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Departamentos;

import com.nexus.biblioNepo.SERVICES.Departamentos.IDepartamentoServiceAdmin;
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
@Tag(name = "Departamento admin")
@RequestMapping(value = "/api/v1/departamentos/admin")
@RestController
public class DepartamentoAdminController {

    private IDepartamentoServiceAdmin departamentoServiceAdmin;

    @Autowired
    public DepartamentoAdminController(IDepartamentoServiceAdmin departamentoServiceAdmin) {
        this.departamentoServiceAdmin = departamentoServiceAdmin;
    }

    @GetMapping(value = "/cargarDatos")
    public ResponseEntity<BasicResponseDto> cargarDepartamentos() {
        
        departamentoServiceAdmin.cargarDepartamentosByAPI();

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Se han cargado con exitos los departamentos al sistema"));
    }

}
