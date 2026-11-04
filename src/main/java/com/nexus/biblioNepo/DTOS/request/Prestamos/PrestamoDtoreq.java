/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.Prestamos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author luis
 */
public class PrestamoDtoreq {

    @Schema(description = "ID del usuario que solicita el préstamo", example = "12", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El ID del usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser un número positivo")
    @JsonProperty("id_user")
    private Long id_user;

    @Schema(description = "ID del libro a prestar", example = "45", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "El ID del libro es obligatorio")
    @Positive(message = "El ID del libro debe ser un número positivo")
    @JsonProperty("id_libro")
    private Long id_libro;

    @Schema(description = "Fecha en que se realiza el préstamo", example = "2026-11-04", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La fecha de préstamo es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("fechaPrestamo")
    private LocalDate fechaPrestamo;

    @Schema(description = "Fecha límite para devolver el libro", example = "2026-11-18", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @FutureOrPresent(message = "La fecha de vencimiento debe ser hoy o una fecha futura")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("fecha_venciimiento")
    private LocalDate fecha_venciimiento;

    @Schema(description = "Hora en que se genera el préstamo", example = "10:30:00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La hora de préstamo es obligatoria")
    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("hora_prestamo")
    private LocalTime hora_prestamo;

    @Schema(description = "Hora límite de vencimiento en el día de entrega", example = "18:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "La hora de vencimiento es obligatoria")
    @JsonFormat(pattern = "HH:mm:ss")
    @JsonProperty("hora_vencimiento")
    private LocalTime hora_vencimiento;

    public PrestamoDtoreq(Long id_user, Long id_libro, LocalDate fechaPrestamo, LocalDate fecha_venciimiento, LocalTime hora_prestamo, LocalTime hora_vencimiento) {
        this.id_user = id_user;
        this.id_libro = id_libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fecha_venciimiento = fecha_venciimiento;
        this.hora_prestamo = hora_prestamo;
        this.hora_vencimiento = hora_vencimiento;
    }

    public PrestamoDtoreq() {
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getId_libro() {
        return id_libro;
    }

    public void setId_libro(Long id_libro) {
        this.id_libro = id_libro;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFecha_venciimiento() {
        return fecha_venciimiento;
    }

    public void setFecha_venciimiento(LocalDate fecha_venciimiento) {
        this.fecha_venciimiento = fecha_venciimiento;
    }

    public LocalTime getHora_prestamo() {
        return hora_prestamo;
    }

    public void setHora_prestamo(LocalTime hora_prestamo) {
        this.hora_prestamo = hora_prestamo;
    }

    public LocalTime getHora_vencimiento() {
        return hora_vencimiento;
    }

    public void setHora_vencimiento(LocalTime hora_vencimiento) {
        this.hora_vencimiento = hora_vencimiento;
    }

}
