/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Users;

import com.nexus.biblioNepo.DTOS.request.Usuarios.UsuarioBasicoDtoReq;
import com.nexus.biblioNepo.SERVICES.User.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(name = "Usuarios",
        description = "Módulo encargado de getsionar las operaciones basicas de los usuarios en el sistema")
@RequestMapping(value = "/api/v1/users")
@RestController
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(description = "Operación encargada de actualizar los datos de un usuario",
            method = "PUT")
    @PutMapping(value = "/{id_user}")
    public ResponseEntity<BasicResponseDto> updateDatosBasicosById(
            @PathVariable(
                    value = "id_user",
                    required = true) Long id_user,
            @Valid
            @RequestBody(required = true) UsuarioBasicoDtoReq usuarioBasicoDtoReq) {

        userService.updateById(id_user, usuarioBasicoDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Usuario actualizado con exito."));

    }

}
