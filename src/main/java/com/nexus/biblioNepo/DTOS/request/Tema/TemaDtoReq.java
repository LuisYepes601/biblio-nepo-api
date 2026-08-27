/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.Tema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author luis
 */
public class TemaDtoReq {

    @NotBlank(message = "El nombre del tema es obligatorio")
    @Size(
            min = 3,
            max = 100,
            message = "El nombre del tema debe tener entre 3 y 100 caracteres"
    )
    @Pattern(
            regexp = "^[\\p{L}\\p{N}]+(?:[ .,'’()\\-/][\\p{L}\\p{N}]+)*$",
            message = "El nombre solo puede contener letras, números, espacios y caracteres de puntuación permitidos"
    )
    private String nombre;

    @Size(
            max = 500,
            message = "La descripción no puede superar los 500 caracteres"
    )
    @Pattern(
            regexp = "^(|[\\p{L}\\p{N}][\\p{L}\\p{N}\\s.,;:!?¿¡()'\"’\\-_/]*[\\p{L}\\p{N}.,;:!?¿¡()'\"’\\-_/])$",
            message = "La descripción contiene caracteres no permitidos"
    )
    private String descripcion;

    public TemaDtoReq(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public TemaDtoReq() {
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
