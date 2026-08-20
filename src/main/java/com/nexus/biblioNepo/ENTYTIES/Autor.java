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
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author luis
 */
@Table(
        name = "autor")
@Entity
public class Autor extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "nombre",
            length = 100,
            nullable = false)
    private String nombre;

    @Column(name = "segundo_nombre",
            length = 100)
    private String segundoNombre;

    @Column(name = "primer_apellido",
            nullable = false,
            length = 100)
    private String primerApellido;

    @Column(name = "seguno_apellido",
            nullable = false,
            length = 100)
    private String segundoApellido;

    @Column(
            name = "fecha_nacimiento",
            nullable = false)
    private LocalDate fechaNacimiento;

    @Column(
            name = "fecha_fallecimiento")
    private LocalDate fechaFallecimiento;

    @Column(name = "url_foto")
    private String urlFoto;

    @Column(name = "public_id_url_foto")
    private String publicIdUrlFoto;

    @Column(name = "is_fallecido",
            nullable = false)
    private Boolean isFallecido;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Boock> books;

    @JoinColumn(name = "id_pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pais nacionalidad;
}
