/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.Libro;

import com.nexus.biblioNepo.DTOS.request.Categoria.CategoriaDtoReq;
import com.nexus.biblioNepo.DTOS.request.PalabraClaveDtoReq;
import com.nexus.biblioNepo.DTOS.request.Tema.TemaDtoReq;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author luis
 */
public class LibroDtoReq {

    private String titulo;

    private String subtitulo;

    private String isbn;

    private String editorial;

    private LocalDate fechaPublicacion;

    private Integer edicion;

    private String descripcion;

    private Integer id_formato_libro;

    private List<CategoriaDtoReq> categorias;

    private Long id_libro;

    private Integer id_autor;

    private List<TemaDtoReq> temas;

    private List<PalabraClaveDtoReq> palabras_claves;

    private Integer id_idioma;
    
    private Integer id_tipo_libro;
    
    private String paisOrigen;

    public LibroDtoReq(String titulo, String subtitulo, String isbn, String editorial, LocalDate fechaPublicacion, Integer edicion, String descripcion, Integer id_formato_libro, List<CategoriaDtoReq> categorias, Long id_libro, Integer id_autor, List<TemaDtoReq> temas, List<PalabraClaveDtoReq> palabras_claves, Integer id_idioma, Integer id_tipo_libro, String paisOrigen) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.isbn = isbn;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
        this.edicion = edicion;
        this.descripcion = descripcion;
        this.id_formato_libro = id_formato_libro;
        this.categorias = categorias;
        this.id_libro = id_libro;
        this.id_autor = id_autor;
        this.temas = temas;
        this.palabras_claves = palabras_claves;
        this.id_idioma = id_idioma;
        this.id_tipo_libro = id_tipo_libro;
        this.paisOrigen = paisOrigen;
    }

  

    public LibroDtoReq() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId_formato_libro() {
        return id_formato_libro;
    }

    public void setId_formato_libro(Integer id_formato_libro) {
        this.id_formato_libro = id_formato_libro;
    }

    public List<CategoriaDtoReq> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDtoReq> categorias) {
        this.categorias = categorias;
    }

    public Long getId_libro() {
        return id_libro;
    }

    public void setId_libro(Long id_libro) {
        this.id_libro = id_libro;
    }

    public Integer getId_autor() {
        return id_autor;
    }

    public void setId_autor(Integer id_autor) {
        this.id_autor = id_autor;
    }

    public List<TemaDtoReq> getTemas() {
        return temas;
    }

    public void setTemas(List<TemaDtoReq> temas) {
        this.temas = temas;
    }

    public List<PalabraClaveDtoReq> getPalabras_claves() {
        return palabras_claves;
    }

    public void setPalabras_claves(List<PalabraClaveDtoReq> palabras_claves) {
        this.palabras_claves = palabras_claves;
    }

    public Integer getId_idioma() {
        return id_idioma;
    }

    public void setId_idioma(Integer id_idioma) {
        this.id_idioma = id_idioma;
    }

    public Integer getId_tipo_libro() {
        return id_tipo_libro;
    }

    public void setId_tipo_libro(Integer id_tipo_libro) {
        this.id_tipo_libro = id_tipo_libro;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

}
