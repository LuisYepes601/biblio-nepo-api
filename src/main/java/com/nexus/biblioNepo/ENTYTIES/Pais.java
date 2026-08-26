/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.ENTYTIES;

import com.nexus.biblioNepo.AUDITORIA.Auditoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author luis
 */
@Table(name = "pais")
@Entity
public class Pais extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "nombre",
            nullable = false,
            length = 100)
    private String nombre;

    @Column(
            name = "iso_3",
            nullable = false)
    private String iso_3;

    @OneToMany(mappedBy = "nacionalidad", fetch = FetchType.LAZY)
    private List<Autor> autores;

    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY)
    private List<departamento> departamentos;
    
    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY)
    private List<direccion>direcciones;

    public Pais(Integer id, String nombre, String iso_3, List<Autor> autores, List<departamento> departamentos, List<direccion> direcciones, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.nombre = nombre;
        this.iso_3 = iso_3;
        this.autores = autores;
        this.departamentos = departamentos;
        this.direcciones = direcciones;
    }

    public Pais() {
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

    public String getIso_3() {
        return iso_3;
    }

    public void setIso_3(String iso_3) {
        this.iso_3 = iso_3;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<direccion> direcciones) {
        this.direcciones = direcciones;
    }

}
