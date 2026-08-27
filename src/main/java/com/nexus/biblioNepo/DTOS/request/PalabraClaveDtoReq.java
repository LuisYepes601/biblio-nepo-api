/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author luis
 */
public class PalabraClaveDtoReq {

    @Schema(
            description = "Nombre de la palabra clave",
            example = "Literatura colombiana",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 2,
            maxLength = 100
    )
    @NotBlank(message = "El nombre es obligatorio")
    @Size(
            min = 2,
            max = 100,
            message = "El nombre debe tener entre 2 y 100 caracteres"
    )
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9]+(?:\\s+[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9]+)*$",
            message = "El nombre solo puede contener letras, números y espacios"
    )
    private String nombre;

    @Schema(
            description = "Descripción de la palabra clave",
            example = "Palabra clave utilizada para identificar obras de literatura colombiana",
            maxLength = 500,
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @Size(
            max = 200,
            message = "La descripción no puede superar los 200 caracteres"
    )
    private String descripcion;

    public PalabraClaveDtoReq(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public PalabraClaveDtoReq() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
