/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.Pais;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author luis
 */
public class PaisDtoReq {

    @Schema(
            description = "Nombre del país",
            example = "Colombia",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El nombre del país no puede estar vacío")
    @Size(
            min = 2,
            max = 100,
            message = "El nombre del país debe contener entre 2 y 100 caracteres"
    )
    @Pattern(
            regexp = "^[\\p{L}]+(?:[ '-][\\p{L}]+)*$",
            message = "El nombre del país solo puede contener letras, espacios, apóstrofes y guiones"
    )
    private String nombre;

    @Schema(
            description = "Código ISO 3166-1 alfa-3 del país",
            example = "COL",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El código ISO 3 no puede estar vacío")
    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "El código ISO 3 debe contener exactamente 3 letras mayúsculas"
    )
    private String iso_3;

    public PaisDtoReq(String nombre, String iso_3) {
        this.nombre = nombre;
        this.iso_3 = iso_3;
    }

    public PaisDtoReq() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIso_3() {
        return iso_3;
    }

    public void setIso_3(String iso_3) {
        this.iso_3 = iso_3;
    }

}
