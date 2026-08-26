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
@Table(name = "departamento",
        indexes = {
            @Index(name = "idx_departamento_nombre", columnList = "nombre"),
            @Index(name = "idx_comp_departamento_nombre_code_postal", columnList = "nombre,code_postal"),
            @Index(name = "idx_comp_departamento_nombre_code_id_pais", columnList = "nombre,code_postal,id_pais")
        })
@Entity
public class departamento {

    @Id
    private Integer id;

    @Column(
            name = "nombre",
            nullable = false,
            length = 100)
    private String nombre;

    @Column(
            name = "code_postal",
            nullable = true,
            length = 50)
    private String codePostal;

    @JoinColumn(name = "id_pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pais pais;

    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<ciudad> ciudades;

    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<direccion> direcciones;

    public departamento(String nombre, String codePostal, Pais pais) {
        this.nombre = nombre;
        this.codePostal = codePostal;
        this.pais = pais;
    }

    public departamento() {
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

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<direccion> direcciones) {
        this.direcciones = direcciones;
    }

}
