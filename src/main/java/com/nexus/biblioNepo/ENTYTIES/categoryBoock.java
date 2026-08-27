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
        name = "category_book",
        indexes = {
            @Index(name = "idx_category_book_nombre", columnList = "nombre")
        })
@Entity
public class categoryBoock extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "nombre",
            nullable = false,
            unique = true,
            length = 100)
    private String nombre;

    @Column(
            columnDefinition = "TEXT",
            length = 200)
    private String descripcion;

    @OneToMany(mappedBy = "categoryBoock", fetch = FetchType.LAZY)
    private List<libro_categoria> boocks;

    public categoryBoock() {
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

    public List<libro_categoria> getBoocks() {
        return boocks;
    }

    public void setBoocks(List<libro_categoria> boocks) {
        this.boocks = boocks;
    }

    public categoryBoock(Integer id, String nombre, String descripcion, List<libro_categoria> boocks, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.boocks = boocks;
    }
    
    

}
