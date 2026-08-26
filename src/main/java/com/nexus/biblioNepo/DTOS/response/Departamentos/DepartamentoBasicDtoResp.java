/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Departamentos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author luis
 */
public class DepartamentoBasicDtoResp {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String nombre;

    public DepartamentoBasicDtoResp(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public DepartamentoBasicDtoResp() {
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
    
    
}
