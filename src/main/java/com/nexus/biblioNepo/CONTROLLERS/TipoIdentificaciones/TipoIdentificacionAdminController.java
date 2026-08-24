/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.TipoIdentificaciones;

import com.nexus.biblioNepo.DTOS.request.TipoIdentificacionReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.TipoIdentificacionAdminResp.TipoIdentificacionDtoAdminResp;
import com.nexus.biblioNepo.DTOS.response.TipoIdentificacionAdminResp.TipoidentificacionDetailsAdminDto;
import com.nexus.biblioNepo.SERVICES.TipoIdenticicacion.ITipoIdentificacionAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(
        name = "Adminitrar Tipo de identificaciones",
        description = "Módulo encargado de administrar los tipos de identificaciones del sistema")
@RequestMapping(value = "/api/v1/admin/tipo-identificaciones")
@RestController
public class TipoIdentificacionAdminController {

    private ITipoIdentificacionAdminService tipoIdentificacionAdminService;

    @Autowired
    public TipoIdentificacionAdminController(ITipoIdentificacionAdminService tipoIdentificacionAdminService) {
        this.tipoIdentificacionAdminService = tipoIdentificacionAdminService;
    }

    @Operation(
            description = "Operación encargada de crear un tipo de identificación en el sistema",
            method = "POST")
    @PostMapping
    public ResponseEntity<BasicResponseDto> create(
            @Valid
            @RequestBody(required = true) TipoIdentificacionReq tipoIdentificacionReq) {

        tipoIdentificacionAdminService.create(tipoIdentificacionReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El tipo de identificación "
                        + tipoIdentificacionReq.getNombre().trim().toUpperCase()));
    }

    @Operation(
            description = "Operación encargada de eliminar un tipo de identificacióon del sistema",
            method = "GET")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> deleteById(
            @PathVariable(
                    name = "id",
                    required = true) Integer id) {

        tipoIdentificacionAdminService.deleteByID(id);
        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El tipo de identificación ha sido eliminado con exito del sistema"));

    }

    @Operation(description = "Operación encaragda de actulizar datos de un tipo de identificación del sistema",
            method = "PUT")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> updateByID(
            @PathVariable(
                    name = "id",
                    required = true) Integer id,
            @Valid
            @RequestBody(required = true) TipoIdentificacionReq tipoIdentificacionReq) {

        tipoIdentificacionAdminService.updateByid(id, tipoIdentificacionReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El tipo de identificación ha sido actualizad con éxito"));

    }

    @Operation(
            description = "Operación encargada de mostrar los tipos de identificaciones con todos sus datos al administrador",
            method = "GET"
    )
    @GetMapping
    public ResponseEntity<PageResponse<TipoIdentificacionDtoAdminResp>> getAllByAdmin(
            @RequestParam(
                    name = "nombre",
                    required = false) String nombre,
            @RequestParam(
                    name = "isDelete",
                    required = false) Boolean isDelete,
            Pageable pageable
    ) {

        return ResponseEntity
                .ok()
                .body(tipoIdentificacionAdminService.getAllAdmin(nombre, isDelete, pageable)
                );
    }

    @Operation(description = "Operación encargada de mostras los dettales de un tipo de identificación",
            method = "GET")
    @GetMapping(value = "/{id}/details")
    public ResponseEntity<TipoidentificacionDetailsAdminDto> getDetailsById(
            @PathVariable(
                    name = "id",
                    required = true) Integer id) {

        return ResponseEntity
                .ok()
                .body(tipoIdentificacionAdminService.getDetailsById(id));
    }

}
