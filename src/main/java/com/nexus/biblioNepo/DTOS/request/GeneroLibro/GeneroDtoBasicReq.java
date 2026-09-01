/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.GeneroLibro;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author luis
 */
public class GeneroDtoBasicReq {

    @Schema(description = "Representa elid del genero seleccionado",
            example = "12546",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El id del genero no puede quedar vacio")
    @Positive(message = "El id de genero debe de ser un numero positivo mayor a 0")
    private Integer id;

    @Schema(description = "Nombre del genero",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El nombre del genero no puede quedar vacio")
    private String nombre;

    public GeneroDtoBasicReq(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public GeneroDtoBasicReq() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
