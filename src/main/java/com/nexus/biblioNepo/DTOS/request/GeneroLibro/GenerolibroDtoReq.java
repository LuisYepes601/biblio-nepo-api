/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.GeneroLibro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author luis
 */
public class GenerolibroDtoReq {

    @NotBlank(message = "El nombre del género es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$",
            message = "El nombre solo puede contener letras y espacios"
    )
    private String nombre;

    @Size(max = 200, message = "La descripción debe tener entre 5 y 20 caracteres")
    private String descripcion;

    public GenerolibroDtoReq(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public GenerolibroDtoReq() {
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
