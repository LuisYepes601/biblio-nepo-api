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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author luis
 */
@Table(
        name = "biblioteca")
@Entity
public class biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre",
            nullable = false,
            length = 150)
    private String nombre;

    @Column(
            name = "tiene_tienda_fisica",
            nullable = false)
    private Boolean tieneSedeFisica;

    @Column(
            name = "descripcion",
            length = 500)
    private String descripcion;

    @OneToMany(mappedBy = "biblioteca", fetch = FetchType.LAZY)
    private List<sede> sedes;

    @Column(
            name = "img_biblioteca")
    private String img_biblioteca;

    @Column(
            name = "public_id_img_biblioteca")
    private String public_id_img_biblioteca;

    public biblioteca(Integer id, String nombre, Boolean tieneSedeFisica, String descripcion, List<sede> sedes, String img_biblioteca, String public_id_img_biblioteca) {
        this.id = id;
        this.nombre = nombre;
        this.tieneSedeFisica = tieneSedeFisica;
        this.descripcion = descripcion;
        this.sedes = sedes;
        this.img_biblioteca = img_biblioteca;
        this.public_id_img_biblioteca = public_id_img_biblioteca;
    }

    public biblioteca() {
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

    public Boolean getTieneSedeFisica() {
        return tieneSedeFisica;
    }

    public void setTieneSedeFisica(Boolean tieneSedeFisica) {
        this.tieneSedeFisica = tieneSedeFisica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<sede> getSedes() {
        return sedes;
    }

    public void setSedes(List<sede> sedes) {
        this.sedes = sedes;
    }

    public String getImg_biblioteca() {
        return img_biblioteca;
    }

    public void setImg_biblioteca(String img_biblioteca) {
        this.img_biblioteca = img_biblioteca;
    }

    public String getPublic_id_img_biblioteca() {
        return public_id_img_biblioteca;
    }

    public void setPublic_id_img_biblioteca(String public_id_img_biblioteca) {
        this.public_id_img_biblioteca = public_id_img_biblioteca;
    }

}
