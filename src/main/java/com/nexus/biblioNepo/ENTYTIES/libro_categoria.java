/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.ENTYTIES;

import com.nexus.biblioNepo.AUDITORIA.Auditoria;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author luis
 */
@Table(
        name = "libro_categoria")
@Entity
public class libro_categoria extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "id_libro", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Boock boock;

    @JoinColumn(name = "id_categoria", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private categoryBoock categoryBoock;

    public libro_categoria(Integer id, Boock boock, categoryBoock categoryBoock, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.boock = boock;
        this.categoryBoock = categoryBoock;
    }

    public libro_categoria() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boock getBoock() {
        return boock;
    }

    public void setBoock(Boock boock) {
        this.boock = boock;
    }

    public categoryBoock getCategoryBoock() {
        return categoryBoock;
    }

    public void setCategoryBoock(categoryBoock categoryBoock) {
        this.categoryBoock = categoryBoock;
    }
    
    
}
