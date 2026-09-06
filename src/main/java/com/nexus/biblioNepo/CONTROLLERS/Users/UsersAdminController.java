/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Users;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Users.UserDtoAdminResponse;
import com.nexus.biblioNepo.SERVICES.User.IUserAdminService;
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
        name = "Administrador de Usuarios",
        description = "Módulo encaragdo de admiistrar los usuarios del sistema, utilizando las distintas operaciones disponibles")
@RequestMapping(value = "/api/v1/users/admin")
@RestController
public class UsersAdminController {

    private IUserAdminService userAdminService;

    @Autowired
    public UsersAdminController(IUserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    @Operation(description = "Operación de mostrar todos los usuarios del sistema",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<UserDtoAdminResponse>> getAll(
            @RequestParam(name = "num_identificacion", required = false) String num_identificacion,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "id_rol", required = false) Integer id_rol,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "primer_apellido", required = false) String primer_apellido,
            @RequestParam(name = "isDelete", required = false) Boolean isDelete,
            Pageable pageable
    ) {

        return ResponseEntity
                .ok()
                .body(userAdminService.getAll(num_identificacion, email, id_rol, nombre, primer_apellido, pageable));
    }

}
