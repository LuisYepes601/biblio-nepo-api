/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.ENTYTIES;

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
import java.util.List;

/**
 *
 * @author luis
 */
@Table(name = "bookc")
@Entity
public class Boock {

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
            unique = true,
            length = 20)
    private String isbn;

    @Column(length = 150)
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

    @JoinColumn(name = "id_category_boock")
    @ManyToOne(fetch = FetchType.LAZY)
    private categoryBoock categoryBoock;

    @JoinColumn(name = "id_idioma")
    @ManyToOne(fetch = FetchType.LAZY)
    private Idiom idiom;

    @OneToMany(mappedBy = "boock", fetch = FetchType.LAZY)
    private List<libro_genero> libros_generos;

    @JoinColumn(name = "id_autor")
    @ManyToOne(fetch = FetchType.LAZY)
    private Autor autor;

}
