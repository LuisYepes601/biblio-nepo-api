/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Direcciones;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author luis
 */
public class DireccionBasicDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String barrio;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String complemento;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String masDetalles;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idPais;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String pais;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idDepartamento;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String departamento;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idCiudad;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String ciudad;

    public DireccionBasicDto() {
    }

    public DireccionBasicDto(Integer id, String barrio, String complemento, String masDetalles, Integer idPais, String pais, Integer idDepartamento, String departamento, Integer idCiudad, String ciudad) {
        this.id = id;
        this.barrio = barrio;
        this.complemento = complemento;
        this.masDetalles = masDetalles;
        this.idPais = idPais;
        this.pais = pais;
        this.idDepartamento = idDepartamento;
        this.departamento = departamento;
        this.idCiudad = idCiudad;
        this.ciudad = ciudad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
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
