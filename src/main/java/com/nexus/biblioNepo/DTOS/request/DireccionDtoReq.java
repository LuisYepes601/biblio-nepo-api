/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 *
 * @author luis
 */
public class DireccionDtoReq {

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "Centro"
    )
    @NotBlank(message = "El barrio no puede quedar vacío")
    @Size(min = 2, max = 100, message = "El barrio debe contener entre 2 y 100 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9 .'-]+$",
            message = "El barrio contiene caracteres no permitidos"
    )
    private String barrio;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1"
    )
    @NotNull(message = "El id de la ciudad es obligatorio")
    @Positive(message = "El id de la ciudad debe ser mayor que 0")
    private Integer id_ciudad;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1"
    )
    @NotNull(message = "El id del departamento es obligatorio")
    @Positive(message = "El id del departamento debe ser mayor que 0")
    private Integer id_departamento;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1"
    )
    @NotNull(message = "El id del país es obligatorio")
    @Positive(message = "El id del país debe ser mayor que 0")
    private Integer id_pais;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "Casa blanca, segundo piso"
    )
    @Size(max = 255, message = "El complemento no puede superar los 255 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9 .,\\-#°'()]+$",
            message = "El complemento contiene caracteres no permitidos"
    )
    private String complemento;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "Frente al parque principal"
    )
    @Size(max = 500, message = "Los detalles no pueden superar los 500 caracteres")
    @Pattern(
            regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ0-9 .,\\-#°'()]+$",
            message = "Los detalles contienen caracteres no permitidos"
    )
    private String masDetalles;

    public DireccionDtoReq(String barrio, Integer id_ciudad, Integer id_departamento, Integer id_pais, String complemento, String masDetalles) {
        this.barrio = barrio;
        this.id_ciudad = id_ciudad;
        this.id_departamento = id_departamento;
        this.id_pais = id_pais;
        this.complemento = complemento;
        this.masDetalles = masDetalles;
    }

    public DireccionDtoReq() {
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Integer getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Integer id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public Integer getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Integer id_departamento) {
        this.id_departamento = id_departamento;
    }

    public Integer getId_pais() {
        return id_pais;
    }

    public void setId_pais(Integer id_pais) {
        this.id_pais = id_pais;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getMasDetalles() {
        return masDetalles;
    }

    public void setMasDetalles(String masDetalles) {
        this.masDetalles = masDetalles;
    }

}
