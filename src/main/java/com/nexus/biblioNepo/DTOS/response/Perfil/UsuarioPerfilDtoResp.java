/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Perfil;

import com.nexus.biblioNepo.DTOS.response.Users.UserDtoAdminResponse;
import java.time.LocalDate;

/**
 *
 * @author luis
 */
public class UsuarioPerfilDtoResp extends UserDtoAdminResponse {

    private String tipoIdentificacion;

    private String rol;

    private String barrio;

    private String complemento;

    private String masDetalles;

    private String pais;

    private String departamento;

    private String ciudad;

    public UsuarioPerfilDtoResp(String tipoIdentificacion, String rol, String barrio, String complemento, String masDetalles, String pais, String departamento, String ciudad, Long id, String nombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, String urlFotoPerfil, String publicIdUrlFotoPerfil, String email, String numeroIdentificacion) {
        super(id, nombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, urlFotoPerfil, publicIdUrlFotoPerfil, email, numeroIdentificacion);
        this.tipoIdentificacion = tipoIdentificacion;
        this.rol = rol;
        this.barrio = barrio;
        this.complemento = complemento;
        this.masDetalles = masDetalles;
        this.pais = pais;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    public UsuarioPerfilDtoResp() {
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getMasDetalles() {
        return masDetalles;
    }

    public void setMasDetalles(String masDetalles) {
        this.masDetalles = masDetalles;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}
