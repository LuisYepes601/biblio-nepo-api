/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.Formatolibro;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author luis
 */
public class FormatolibroDtoReq {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Digital")
    @NotBlank(message = "El nombre es obligatorio")
    @Size(
            min = 2,
            max = 100,
            message = "El nombre debe tener entre 2 y 100 caracteres"
    )
    @Pattern(
            regexp = "^[\\p{L}0-9]+(?:[ '\\-][\\p{L}0-9]+)*$",
            message = "El nombre solo puede contener letras, números, espacios y guiones"
    )
    private String nombre;

    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Size(
            min = 10,
            max = 200,
            message = "La descripción debe tener entre 10 y 200 caracteres"
    )
    private String descripcion;

    public FormatolibroDtoReq(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public FormatolibroDtoReq() {
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
