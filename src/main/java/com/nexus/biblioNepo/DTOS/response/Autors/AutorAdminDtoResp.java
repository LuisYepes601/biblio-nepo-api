/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Autors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author luis
 */
public class AutorAdminDtoResp {

    private Integer id;

    private String nombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    private LocalDate fechaNacimiento;
    private LocalDate fechaFallecimiento;

    private String urlFoto;

    private Boolean isFallecido;

    private Integer idPais;
    private String pais;

    private Boolean isDelete;

    public AutorAdminDtoResp(Integer id, String nombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, LocalDate fechaFallecimiento, String urlFoto, Boolean isFallecido, Integer idPais, String pais, Boolean isDelete) {
        this.id = id;
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.urlFoto = urlFoto;
        this.isFallecido = isFallecido;
        this.idPais = idPais;
        this.pais = pais;
        this.isDelete = isDelete;
    }

 

    public AutorAdminDtoResp() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDate getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(LocalDate fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Boolean getIsFallecido() {
        return isFallecido;
    }

    public void setIsFallecido(Boolean isFallecido) {
        this.isFallecido = isFallecido;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    
    

}
