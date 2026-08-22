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
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author luis
 */
@Table(
        name = "rol",
        indexes = {
            @Index(name = "idx_rol_nombre", columnList = "nombre")
        })
@Entity
public class rol extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "nombre",
            nullable = false,
            length = 100)
    private String nombre;

    @Column(
            name = "descripcion",
            length = 200)
    private String descripcion;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<usuario> usuarios;

    public rol(Integer id, String nombre, String descripcion, List<usuario> usuarios, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.usuarios = usuarios;
    }

    public rol() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
