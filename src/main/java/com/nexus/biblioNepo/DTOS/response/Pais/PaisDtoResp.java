/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Pais;

/**
 *
 * @author luis
 */
public class PaisDtoResp {

    private Integer id;

    private String nombre;

    public PaisDtoResp(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public PaisDtoResp() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
