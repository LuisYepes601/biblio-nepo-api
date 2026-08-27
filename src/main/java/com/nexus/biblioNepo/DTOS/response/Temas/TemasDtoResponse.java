/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Temas;

/**
 *
 * @author luis
 */
public class TemasDtoResponse {

    private Integer id;
    
    private String nombre;

    public TemasDtoResponse(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TemasDtoResponse() {
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
