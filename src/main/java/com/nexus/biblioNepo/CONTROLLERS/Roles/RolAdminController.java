/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Roles;

import com.nexus.biblioNepo.DTOS.request.RolDtoAdminReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDetailsAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDtoAdminResp;
import com.nexus.biblioNepo.SERVICES.Roles.IRolAdmin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
        name = "Administración de Roles",
        description = "Módulo encargado de admintrar y gestionar los roles del sistema")
@RequestMapping(value = "/api/v1/admin/roles")
@RestController
public class RolAdminController {

    private IRolAdmin rolAdminService;

    @Autowired
    public RolAdminController(IRolAdmin rolAdminService) {
        this.rolAdminService = rolAdminService;
    }

    @Operation(description = "Operación encaragda de crear roles en el sistema",
            method = "POST")
    @PostMapping
    public ResponseEntity<BasicResponseDto> create(
            @RequestBody(required = true) RolDtoAdminReq rolDtoAdminReq) {

        rolAdminService.create(rolDtoAdminReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El rol " + rolDtoAdminReq.getNombre().trim().toUpperCase() + " se hacreado con exito en el sitema"));

    }

    @Operation(
            description = "Operración encargada de actualizar datos de un rol",
            method = "PUT")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> updateByID(
            @PathVariable(
                    name = "id",
                    required = true) Integer id,
            @RequestBody(required = true) RolDtoAdminReq rolDtoAdminReq) {

        rolAdminService.updateByID(id, rolDtoAdminReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El rol ha sido actualizado con exito."));
    }

    @Operation(
            description = "Operación encargada de eliminar un rol del sistema",
            method = "DELETE")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> deleteByID(
            @PathVariable(
                    name = "id",
                    required = true) Integer id) {

        rolAdminService.deleteByID(id);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El rol ha sido eliminado con exito del sistema"));
    }

    @Operation(
            description = "Operación encargada de mostrar los roles del sistema",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<RolDtoAdminResp>> getAllAdmin(
            @RequestParam(
                    name = "name",
                    required = false) String name,
            @RequestParam(
                    name = "isDelete",
                    required = false) Boolean isDelete,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(rolAdminService.getAllAdmin(name, isDelete, pageable));
    }

    @Operation(
            description = "Operación encaragda de mostrar los detalles de un rol",
            method = "GET")
    @GetMapping(value = "/{id}/details")
    public ResponseEntity<RolDetailsAdminDtoResp> getDetailsById(
            @PathVariable(
                    name = "id",
                    required = true) Integer id) {

        return ResponseEntity
                .ok()
                .body(rolAdminService.getDetailByID(id));
    }
}
