
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.PalabarasClave;

import com.nexus.biblioNepo.DTOS.request.PalabraClaveDtoReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.PalabrasClaves.PalabraClaveDtoResp;
import com.nexus.biblioNepo.ENTYTIES.palabra_clave;
import com.nexus.biblioNepo.SERVICES.PalabrasClave.IPalabrasClave;
import com.nexus.biblioNepo.SERVICES.PalabrasClave.IPalabrasClaveServiceAdmin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Palabras clave",
        description = "Módulo encargado de gestionar las operaciones basicas sobre las palabras claves que existen en cada libro")
@RequestMapping(value = "/api/v1/palabras-clave")
@RestController
public class PalabrasClaveController {

    private IPalabrasClave palabrasClaveService;

    @Autowired
    public PalabrasClaveController(IPalabrasClave palabrasClaveService) {
        this.palabrasClaveService = palabrasClaveService;
    }

    @Operation(description = "Operacion emcaragda de ver las palabras calves de un libro")
    @GetMapping(value = "/{id_libro}")
    public ResponseEntity<PageResponse<PalabraClaveDtoResp>> getPalabrasClaveByIdLibro(
            @PathVariable(
                    name = "id_libro",
                    required = true) Long id_libro,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(palabrasClaveService.getPalabrasClavesbyIdLibro(id_libro, pageable));
    }

    @Operation(description = "Operación encaragda de actualizar palabras clave de un libro")
    @PutMapping()
    public ResponseEntity<BasicResponseDto> updateByIdPalabraAndLibro(
            @RequestParam(
                    name = "id_palabra",
                    required = true) Integer id_palabra,
            @RequestParam(
                    name = "id_libro",
                    required = true) Long id_libro,
            @RequestBody(required = true) PalabraClaveDtoReq palabraClaveDtoReq) {

        palabrasClaveService.editarPalabraClave(id_palabra, id_libro, palabraClaveDtoReq);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("La palabra clave ha sido actualizada con exito"));
    }

    @Operation(description = "Operación encargada de crear palabras claves a un libro",
            method = "POST")
    @PostMapping(value = "/{id_libro}")
    public ResponseEntity<BasicResponseDto> createPalabrasClavesByIdLibro(
            @PathVariable(
                    name = "id_libro",
                    required = true) Long id_libro,
            @RequestBody(required = true) List<PalabraClaveDtoReq> palabras_clave) {

        palabrasClaveService.createByIdLibro(palabras_clave, id_libro);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("Las palbaras claves han sido agregadas al libro"));
    }

}
