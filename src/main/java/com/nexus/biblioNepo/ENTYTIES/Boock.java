/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.ENTYTIES;

import com.nexus.biblioNepo.AUDITORIA.Auditoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author luis
 */
@Table(name = "bookc")
@Entity
public class Boock extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo",
            nullable = false,
            length = 200)
    private String titulo;

    @Column(name = "subtitulo",
            length = 200)
    private String subtitulo;

    @Column(name = "isbn",
            length = 20)
    private String isbn;

    @Column(
            nullable = false,
            length = 150)
    private String editorial;

    private LocalDate fechaPublicacion;

    private Integer edicion;

    private Integer numeroPaginas;

    @Column(length = 500)
    private String portadaUrl;

    @Column(length = 255)
    private String publicIdPortada;

    @Column(length = 500)
    private String archivoUrl;

    @Column(length = 255)
    private String publicIdArchivo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 100)
    private String paisPublicacion;

    @JoinColumn(name = "id_formato_libro")
    @ManyToOne(fetch = FetchType.LAZY)
    private formatoLibro formatoLibro;

    @OneToMany(mappedBy = "boock", fetch = FetchType.LAZY)
    private List<libro_categoria> categorias;

    @JoinColumn(name = "id_idioma")
    @ManyToOne(fetch = FetchType.LAZY)
    private Idiom idiom;

    @OneToMany(mappedBy = "boock", fetch = FetchType.LAZY)
    private List<libro_genero> libros_generos;

    @JoinColumn(name = "id_autor")
    @ManyToOne(fetch = FetchType.LAZY)
    private Autor autor;

    @OneToMany(mappedBy = "boock", fetch = FetchType.LAZY)
    private List<libro_tema> temas;

    @OneToMany(mappedBy = "boock", fetch = FetchType.LAZY)
    private List<palabra_clave_libro> palabras_claves;

    @JoinColumn(name = "id_tipo_libro", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private tipoLibro tipoLibro;

    @OneToMany(mappedBy = "boock", fetch = FetchType.LAZY)
    private List<libro_favorito> usuarios_favoritos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boock")
    private List<prestamo> prestamos;

    public Boock(Long id, String titulo, String subtitulo, String isbn, String editorial, LocalDate fechaPublicacion, Integer edicion, Integer numeroPaginas, String portadaUrl, String publicIdPortada, String archivoUrl, String publicIdArchivo, String descripcion, String paisPublicacion, formatoLibro formatoLibro, List<libro_categoria> categorias, Idiom idiom, List<libro_genero> libros_generos, Autor autor, List<libro_tema> temas, List<palabra_clave_libro> palabras_claves, tipoLibro tipoLibro, List<libro_favorito> usuarios_favoritos, List<prestamo> prestamos, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.isbn = isbn;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
        this.edicion = edicion;
        this.numeroPaginas = numeroPaginas;
        this.portadaUrl = portadaUrl;
        this.publicIdPortada = publicIdPortada;
        this.archivoUrl = archivoUrl;
        this.publicIdArchivo = publicIdArchivo;
        this.descripcion = descripcion;
        this.paisPublicacion = paisPublicacion;
        this.formatoLibro = formatoLibro;
        this.categorias = categorias;
        this.idiom = idiom;
        this.libros_generos = libros_generos;
        this.autor = autor;
        this.temas = temas;
        this.palabras_claves = palabras_claves;
        this.tipoLibro = tipoLibro;
        this.usuarios_favoritos = usuarios_favoritos;
        this.prestamos = prestamos;
    }

    public Boock() {
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

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
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

    public String getArchivoUrl() {
        return archivoUrl;
    }

    public void setArchivoUrl(String archivoUrl) {
        this.archivoUrl = archivoUrl;
    }

    public String getPublicIdArchivo() {
        return publicIdArchivo;
    }

    public void setPublicIdArchivo(String publicIdArchivo) {
        this.publicIdArchivo = publicIdArchivo;
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

    public formatoLibro getFormatoLibro() {
        return formatoLibro;
    }

    public void setFormatoLibro(formatoLibro formatoLibro) {
        this.formatoLibro = formatoLibro;
    }

    public List<libro_categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<libro_categoria> categorias) {
        this.categorias = categorias;
    }

    public Idiom getIdiom() {
        return idiom;
    }

    public void setIdiom(Idiom idiom) {
        this.idiom = idiom;
    }

    public List<libro_genero> getLibros_generos() {
        return libros_generos;
    }

    public void setLibros_generos(List<libro_genero> libros_generos) {
        this.libros_generos = libros_generos;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<libro_tema> getTemas() {
        return temas;
    }

    public void setTemas(List<libro_tema> temas) {
        this.temas = temas;
    }

    public List<palabra_clave_libro> getPalabras_claves() {
        return palabras_claves;
    }

    public void setPalabras_claves(List<palabra_clave_libro> palabras_claves) {
        this.palabras_claves = palabras_claves;
    }

    public tipoLibro getTipoLibro() {
        return tipoLibro;
    }

    public void setTipoLibro(tipoLibro tipoLibro) {
        this.tipoLibro = tipoLibro;
    }

    public List<libro_favorito> getUsuarios_favoritos() {
        return usuarios_favoritos;
    }

    public void setUsuarios_favoritos(List<libro_favorito> usuarios_favoritos) {
        this.usuarios_favoritos = usuarios_favoritos;
    }

    public List<prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<prestamo> prestamos) {
        this.prestamos = prestamos;
    }

}
