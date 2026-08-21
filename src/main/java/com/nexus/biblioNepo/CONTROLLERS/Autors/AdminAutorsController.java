/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Autors;

import com.nexus.biblioNepo.DTOS.request.autorDtoReq;
import com.nexus.biblioNepo.DTOS.response.Autors.AutorAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.IAdminAutors;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(
        name = "Administración de Autores",
        description = "Módulo encargado de administrar las operaciones y funcionalidades relacionadas con los autores del sistema.")
@RequestMapping(value = "/api/v1/autors")
@RestController
public class AdminAutorsController {

    private IAdminAutors adminAutorServices;

    @Autowired
    public AdminAutorsController(IAdminAutors adminAutorServices) {
        this.adminAutorServices = adminAutorServices;
    }

    @Operation(description = "Encargada de crear autores en el sistemas")
    @PostMapping
    public ResponseEntity<BasicResponseDto> create(
            @Valid
            @RequestPart(name = "imgAutor", required = false) MultipartFile imgAutor,
            @RequestPart(name = "body", required = true) autorDtoReq dtoReq) {

        adminAutorServices.create(imgAutor, dtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El autor ha sido creado exitosamente en el sistema"));
    }

    @Operation(
            description = "Operación encargada de actualizar datos de un autor registrado en el sistema",
            method = "PUT")
    @PutMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> updateByID(
            @PathVariable(
                    name = "id",
                    required = true) Integer id,
            @RequestPart(
                    name = "imgAutor",
                    required = false) MultipartFile imgAutor,
            @RequestPart(
                    name = "body",
                    required = true) autorDtoReq dtoReq
    ) {

        adminAutorServices.updateAutorByID(id, imgAutor, dtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El autor ha sido modificado con exito del sistema"));
    }

    @Operation(
            description = "Operación encargada de eliminar un autor del sistema",
            method = "DELETE")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BasicResponseDto> deleteById(
            @PathVariable(
                    name = "id",
                    required = true) Integer id) {

        adminAutorServices.deleteById(id);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El autor se ha eliminado correctamente del sistema"));
    }

    @Operation(
            description = "Operción encargada de mostrar lso autores segun los filtro seleccionados",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<AutorAdminDtoResp>> getAll(
            @RequestParam(
                    value = "name",
                    required = false) String name,
            @RequestParam(
                    value = "id_pais",
                    required = false) Integer idPais,
            @RequestParam(
                    value = "is_delete",
                    required = false) Boolean isDelete,
            @RequestParam(
                    value = "name_boock",
                    required = false) String nameBook,
            @RequestParam(
                    value = "id_categoria_boock",
                    required = false) Integer idCategoriaBook,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(adminAutorServices.getAll(
                        name, idPais, isDelete, nameBook, idCategoriaBook, pageable));
    }

}
