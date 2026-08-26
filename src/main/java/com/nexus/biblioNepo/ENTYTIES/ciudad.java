/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.ENTYTIES;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author luis
 */
@Table(
        name = "ciudad",
        indexes = {
            @Index(name = "idx_ciudad_nombre", columnList = "nombre"),
            @Index(name = "idx_ciudad_id_departamento", columnList = "id_departamento")
        })
@Entity
public class ciudad {

    @Id
    private Integer id;

    @Column(
            name = "nombre",
            nullable = false,
            length = 100)
    private String nombre;

    @JoinColumn(name = "id_departamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private departamento departamento;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ciudad")
    private List<direccion> direcciones;

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

    public departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(departamento departamento) {
        this.departamento = departamento;
    }

    public List<direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<direccion> direcciones) {
        this.direcciones = direcciones;
    }

}
