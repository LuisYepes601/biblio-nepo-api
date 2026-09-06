/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Users;

import java.time.LocalDate;

/**
 *
 * @author luis
 */
public class UserDtoAdminResponse {

    private Long id;

    private String nombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private LocalDate fechaNacimiento;

    private String urlFotoPerfil;

    private String publicIdUrlFotoPerfil;

    private String email;

    private String numeroIdentificacion;

    public UserDtoAdminResponse(Long id, String nombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, String urlFotoPerfil, String publicIdUrlFotoPerfil, String email, String numeroIdentificacion) {
        this.id = id;
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.urlFotoPerfil = urlFotoPerfil;
        this.publicIdUrlFotoPerfil = publicIdUrlFotoPerfil;
        this.email = email;
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public UserDtoAdminResponse() {
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

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }
    
    
}
