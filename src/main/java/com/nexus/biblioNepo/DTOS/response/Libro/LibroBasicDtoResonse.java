/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Libro;

import java.time.LocalDate;

/**
 *
 * @author luis
 */
public class LibroBasicDtoResonse {

    private Long id;

    private String titulo;

    private String subtitulo;

    private String isbn;

    private String editorial;

    private LocalDate fechaPublicacion;

    private Integer edicion;

    private String portadaUrl;

    private String publicIdPortada;

    private String descripcion;

    private String paisPublicacion;

    private String formatoLibro;

    private Long id_formatos_libro;

    private Integer id_autor;

    private String autor;

    public LibroBasicDtoResonse(Long id, String titulo, String subtitulo, String isbn, String editorial, LocalDate fechaPublicacion, Integer edicion, String portadaUrl, String publicIdPortada, String descripcion, String paisPublicacion, String formatoLibro, Long id_formatos_libro, Integer id_autor, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.isbn = isbn;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
        this.edicion = edicion;
        this.portadaUrl = portadaUrl;
        this.publicIdPortada = publicIdPortada;
        this.descripcion = descripcion;
        this.paisPublicacion = paisPublicacion;
        this.formatoLibro = formatoLibro;
        this.id_formatos_libro = id_formatos_libro;
        this.id_autor = id_autor;
        this.autor = autor;
    }

    public LibroBasicDtoResonse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPublicIdPortada() {
        return publicIdPortada;
    }

    public void setPublicIdPortada(String publicIdPortada) {
        this.publicIdPortada = publicIdPortada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPaisPublicacion() {
        return paisPublicacion;
    }

    public void setPaisPublicacion(String paisPublicacion) {
        this.paisPublicacion = paisPublicacion;
    }

    public String getFormatoLibro() {
        return formatoLibro;
    }

    public void setFormatoLibro(String formatoLibro) {
        this.formatoLibro = formatoLibro;
    }

    public Long getId_formatos_libro() {
        return id_formatos_libro;
    }

    public void setId_formatos_libro(Long id_formatos_libro) {
        this.id_formatos_libro = id_formatos_libro;
    }

    public Integer getId_autor() {
        return id_autor;
    }

    public void setId_autor(Integer id_autor) {
        this.id_autor = id_autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    

}
