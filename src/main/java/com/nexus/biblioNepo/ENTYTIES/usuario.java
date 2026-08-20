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
@Table(
        name = "usuario")
@Entity
public class usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "nombre",
            nullable = false,
            length = 100)
    private String nombre;

    @Column(
            name = "segundo_nombre",
            length = 100)
    private String segundoNombre;

    @Column(
            name = "primer_apellido",
            nullable = false,
            length = 100)
    private String primerApellido;

    @Column(
            name = "segundo_apellido",
            nullable = false,
            length = 100)
    private String segundoApellido;

    @Column(
            name = "fecha_nacimiento",
            nullable = false)
    private LocalDate fechaNacimiento;

    @Column(
            name = "url_foto_perfil",
            length = 500)
    private String urlFotoPerfil;

    @Column(name = "public_id_url_foto_perfil",
            length = 255)
    private String publicIdUrlFotoPerfil;

    @Column(
            name = "numero_identificacion",
            nullable = false,
            unique = true,
            length = 30)
    private String numeroIdentificacion;

    @JoinColumn(name = "id_tipo_identificacion", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private tipoidentificacion tipoIdentificacion;

    @JoinColumn(name = "id_rol", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private rol rol;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<libro_favorito> librosFavoritos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<prestamo> prestamos;

}
