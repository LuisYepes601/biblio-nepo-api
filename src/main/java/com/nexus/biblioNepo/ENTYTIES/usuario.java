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

    @Column(name = "email",
            nullable = false)
    private String email;

    @Column(name = "password",
            nullable = false)
    private String password;

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

    @JoinColumn(name = "id_direccion")
    @ManyToOne(fetch = FetchType.LAZY)
    private direccion direccion;

    public usuario(Long id, String nombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, String urlFotoPerfil, String publicIdUrlFotoPerfil, String email, String password, String numeroIdentificacion, tipoidentificacion tipoIdentificacion, rol rol, List<libro_favorito> librosFavoritos, List<prestamo> prestamos, direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.urlFotoPerfil = urlFotoPerfil;
        this.publicIdUrlFotoPerfil = publicIdUrlFotoPerfil;
        this.email = email;
        this.password = password;
        this.numeroIdentificacion = numeroIdentificacion;
        this.tipoIdentificacion = tipoIdentificacion;
        this.rol = rol;
        this.librosFavoritos = librosFavoritos;
        this.prestamos = prestamos;
        this.direccion = direccion;
    }

    public usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }

    public String getPublicIdUrlFotoPerfil() {
        return publicIdUrlFotoPerfil;
    }

    public void setPublicIdUrlFotoPerfil(String publicIdUrlFotoPerfil) {
        this.publicIdUrlFotoPerfil = publicIdUrlFotoPerfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public tipoidentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(tipoidentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public rol getRol() {
        return rol;
    }

    public void setRol(rol rol) {
        this.rol = rol;
    }

    public List<libro_favorito> getLibrosFavoritos() {
        return librosFavoritos;
    }

    public void setLibrosFavoritos(List<libro_favorito> librosFavoritos) {
        this.librosFavoritos = librosFavoritos;
    }

    public List<prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(direccion direccion) {
        this.direccion = direccion;
    }

}
