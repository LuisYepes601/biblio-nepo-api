/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Libros;

import com.nexus.biblioNepo.DTOS.request.Categoria.CategoriaAsignarDto;
import com.nexus.biblioNepo.DTOS.request.GeneroLibro.GeneroDtoBasicReq;
import com.nexus.biblioNepo.DTOS.request.Libro.LibroDtoReq;
import com.nexus.biblioNepo.DTOS.request.autorDtoReq;
import com.nexus.biblioNepo.DTOS.response.Libro.LibroAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.SERVICES.Libros.IlibroServiceAdmin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@Tag(name = "Libros",
        description = "Módulo encargado de gestionar distintas operaciones sobre los libros del sistema que "
        + "no soliciten permisos especiales.")
@RequestMapping(value = "/api/v1/libros/admin")
@RestController
public class LibroAdminController {

    private IlibroServiceAdmin serviceAdmin;

    @Autowired
    public LibroAdminController(IlibroServiceAdmin serviceAdmin) {
        this.serviceAdmin = serviceAdmin;
    }

    @Operation(description = "Operación encaragda de registrar un libro a la biblioteca.",
            method = "POST")
    @PostMapping()
    public ResponseEntity<BasicResponseDto> createBoock(
            @RequestPart(
                    name = "libroDtoReq",
                    required = true) LibroDtoReq libroDtoReq,
            @RequestPart(
                    name = "portada",
                    required = true) MultipartFile portada,
            @RequestPart(
                    name = "libro",
                    required = true) MultipartFile libro,
            @RequestPart(
                    name = "fotoAutor",
                    required = false) MultipartFile fotoAutor,
            @RequestPart(
                    name = "categorias",
                    required = true) List<@Valid CategoriaAsignarDto> categorias,
            @RequestParam(
                    name = "id_autor",
                    required = false) Integer id_autor,
            @RequestPart(
                    name = "autorDtoReq",
                    required = false) autorDtoReq autorDtoReq,
            @RequestPart(
                    name = "generos",
                    required = false) List<@Valid GeneroDtoBasicReq> generos) {

        serviceAdmin.create(libroDtoReq, portada, libro, fotoAutor, categorias, id_autor, autorDtoReq, generos);

        return ResponseEntity
                .ok()
                .body(new BasicResponseDto("El kibro ha sido agregado al sistema correctamente"));

    }

    @Operation(description = "Operación encargada de mostrar los libros del sistema segun filtro",
            method = "GET")
    @GetMapping()
    public ResponseEntity<PageResponse<LibroAdminDtoResp>> getAll(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "id_lib", required = false) Long id_lib,
            @RequestParam(value = "isDelete", required = false) Boolean isDelete,
            @RequestParam(value = "excluyed_by_id", required = false) Long excluyed_by_id,
            @RequestParam(value = "nombre_autor", required = false) String nombre_autor,
            @RequestParam(value = "id_autor", required = false) Integer id_autor,
            @RequestParam(value = "id_cat", required = false) Integer id_cat,
            @RequestParam(value = "id_genero", required = false) Integer id_genero,
            @RequestParam(value = "id_idiom", required = false) Integer id_idiom,
            @RequestParam(value = "id_tipo_libro", required = false) Integer id_tipo_libro,
            Pageable pageable) {

        return ResponseEntity
                .ok()
                .body(serviceAdmin.getAll(nombre, id_lib, isDelete, excluyed_by_id, nombre_autor,
                        id_autor, id_cat, id_genero, id_idiom, id_tipo_libro, pageable));
    }

}
