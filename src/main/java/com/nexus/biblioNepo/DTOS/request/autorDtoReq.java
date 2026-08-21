/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 *
 * @author luis
 */
public class autorDtoReq {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Jhon"
    )
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100,
            message = "El nombre debe tener entre 2 y 100 caracteres")
    @Pattern(
            regexp = "^[\\p{L}]+(?:[ '\\-][\\p{L}]+)*$",
            message = "El nombre contiene caracteres no válidos"
    )
    private String nombre;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "Jose"
    )
    @Size(max = 100,
            message = "El segundo nombre no puede superar los 100 caracteres")
    @Pattern(
            regexp = "^[\\p{L}]+(?:[ '\\-][\\p{L}]+)*$",
            message = "El segundo nombre contiene caracteres no válidos"
    )
    private String segundoNombre;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Menendez"
    )
    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(min = 2, max = 100,
            message = "El primer apellido debe tener entre 2 y 100 caracteres")
    @Pattern(
            regexp = "^[\\p{L}]+(?:[ '\\-][\\p{L}]+)*$",
            message = "El primer apellido contiene caracteres no válidos"
    )
    private String primerApellido;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Peréz"
    )
    @NotBlank(message = "El segundo apellido es obligatorio")
    @Size(min = 2, max = 100,
            message = "El segundo apellido debe tener entre 2 y 100 caracteres")
    @Pattern(
            regexp = "^[\\p{L}]+(?:[ '\\-][\\p{L}]+)*$",
            message = "El segundo apellido contiene caracteres no válidos"
    )
    private String segundoApellido;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
    private LocalDate fechaNacimiento;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @PastOrPresent(message = "La fecha de fallecimiento no puede ser futura")
    private LocalDate fechaFallecimiento;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "El estado de fallecimiento es obligatorio")
    private Boolean isFallecido;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "El país de nacionalidad es obligatorio")
    @Positive(message = "El id del país debe ser positivo")
    private Integer idPais;

    public autorDtoReq(String nombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, LocalDate fechaFallecimiento, Boolean isFallecido, Integer idPais) {
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.isFallecido = isFallecido;
        this.idPais = idPais;
    }

    public autorDtoReq() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(LocalDate fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public Boolean getIsFallecido() {
        return isFallecido;
    }

    public void setIsFallecido(Boolean isFallecido) {
        this.isFallecido = isFallecido;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }
    
    
}
