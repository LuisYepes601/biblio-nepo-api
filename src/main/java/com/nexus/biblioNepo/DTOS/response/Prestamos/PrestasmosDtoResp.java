/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Prestamos;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author luis
 */
public class PrestasmosDtoResp {

    private Integer id_prestamo;

    private LocalDate fechaPrestamo;

    private LocalDate fecha_venciimiento;
    private LocalTime hora_prestamo;
    private LocalTime hora_vencimiento;

    private Long id_libro;

    private String titulo;

    private String subtitulo;

    private String isbn;

    private String editorial;

    private LocalDate fechaPublicacion;

    private Integer edicion;

    private String portadaUrl;

    public PrestasmosDtoResp(Integer id_prestamo, LocalDate fechaPrestamo, LocalDate fecha_venciimiento, LocalTime hora_prestamo, LocalTime hora_vencimiento, Long id_libro, String titulo, String subtitulo, String isbn, String editorial, LocalDate fechaPublicacion, Integer edicion, String portadaUrl) {
        this.id_prestamo = id_prestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fecha_venciimiento = fecha_venciimiento;
        this.hora_prestamo = hora_prestamo;
        this.hora_vencimiento = hora_vencimiento;
        this.id_libro = id_libro;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.isbn = isbn;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
        this.edicion = edicion;
        this.portadaUrl = portadaUrl;
    }

    public PrestasmosDtoResp() {
    }

    public Integer getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(Integer id_prestamo) {
        this.id_prestamo = id_prestamo;
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

    public Long getId_libro() {
        return id_libro;
    }

    public void setId_libro(Long id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getEdicion() {
        return edicion;
    }

    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    public String getPortadaUrl() {
        return portadaUrl;
    }

    public void setPortadaUrl(String portadaUrl) {
        this.portadaUrl = portadaUrl;
    }

}
