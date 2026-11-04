/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Prestamos;

import com.nexus.biblioNepo.DTOS.request.Prestamos.PrestamoDtoreq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Prestamos.PrestasmosDtoResp;
import com.nexus.biblioNepo.SERVICES.Prestamos.IPrestamosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurante_gratitude.demp.DTOS.Global.BasicResponseDto;

/**
 *
 * @author luis
 */
@Tag(name = "Prestamos de libros")
@RequestMapping(value = "/api/v1/prestamos")
@RestController()
public class PrestamosController {
    
    private IPrestamosService prestamosService;
    
    public PrestamosController(IPrestamosService prestamosService) {
        this.prestamosService = prestamosService;
    }
    
    @Operation(description = "Operación encargada de realizar un prestamo de libros en el sistema",
            method = "POST")
    @PostMapping()
    public ResponseEntity<BasicResponseDto> prestarLibro(
            @Valid
            @RequestBody(required = true) PrestamoDtoreq prestamoDtoreq
    ) {
        
        prestamosService.prestarLibro(prestamoDtoreq);
        
        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Nuevo prestamo exitoso."));
    }
    
    @Operation(description = "Operación encargada de mostrar lso prestamos de un usuario",
            method = "GET")
    @GetMapping(value = "/{id_user}")
    public ResponseEntity<PageResponse<PrestasmosDtoResp>> getPrestamosByIDUser(
            @PathVariable(name = "id_user", required = true) Long id_user,
            Pageable pageable
    ) {
        
        return ResponseEntity
                .ok()
                .body(prestamosService.getMisPrestamos(id_user, pageable));
    }
}
