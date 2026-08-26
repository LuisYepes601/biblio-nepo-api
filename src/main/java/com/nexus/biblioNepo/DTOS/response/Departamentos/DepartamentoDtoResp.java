/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Departamentos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author luis
 */
public class DepartamentoDtoResp {

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("cityCapital")
    private cityCapital cityCapital;

    public DepartamentoDtoResp(String nombre, cityCapital cityCapital) {
        this.nombre = nombre;
        this.cityCapital = cityCapital;
    }

    public DepartamentoDtoResp() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public cityCapital getCityCapital() {
        return cityCapital;
    }

    public void setCityCapital(cityCapital cityCapital) {
        this.cityCapital = cityCapital;
    }

}
