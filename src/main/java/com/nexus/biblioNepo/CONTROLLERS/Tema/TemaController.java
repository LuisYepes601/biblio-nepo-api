/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Tema;

import com.nexus.biblioNepo.DTOS.request.Tema.TemaDtoReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Temas.TemasDtoResponse;
import com.nexus.biblioNepo.SERVICES.Tema.ITemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
@Tag(name = "Temas de Libro",
        description = "Módulo encaragado de gestionar los temas de un libro")
@RequestMapping(value = "/api/v1/tema-libros")
@RestController
public class TemaController {

    private ITemaService temaService;

    @Autowired
    public TemaController(ITemaService temaService) {
        this.temaService = temaService;
    }

    @Operation(description = "Operación encargada de asignarles temas a un libro",
            method = "POST")
    @PostMapping(value = "/{id_libro}")
    public ResponseEntity<BasicResponseDto> asignarTemasALibro(
            @PathVariable(
                    name = "id_libro",
                    required = true) long id_libro,
            @Valid
            @RequestBody(required = true) List< TemaDtoReq> temas) {

        temaService.createtemaLibro(id_libro, temas);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Se le ha asignado con exito temas al sistema"));
    }

    @Operation(description = "Operación encaragda de modificar los temas de un libro",
            method = "PUT")
    @PutMapping()
    public ResponseEntity<BasicResponseDto> updateByIdTemaAndLibro(
            @RequestParam(
                    name = "id_tema",
                    required = true) Integer id_tema,
            @RequestParam(
                    name = "id_libro",
                    required = true) Long id_libro,
            @Valid
            @RequestBody(required = true) TemaDtoReq temaDtoReq
    ) {

        temaService.updateById(id_tema, id_libro, temaDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El tema ha sido editado correctamente"));
    }

    @Operation(description = "Operación encargada de mostrar los temas activos de un libro",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<TemasDtoResponse>> getAll(
            @RequestParam(name = "id_libro",
                    required = true) Long id_libro,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(temaService.getAll(id_libro, pageable));

    }
}
