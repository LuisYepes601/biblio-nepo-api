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
@Table(name = "palabra_clave_libro")
@Entity
public class palabra_clave_libro extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JoinColumn(name = "id_libro", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Boock boock;
    
    @JoinColumn(name = "id_palabra_clave", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private palabra_clave palabra_clave;

    public palabra_clave_libro(Integer id, Boock boock, palabra_clave palabra_clave, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.boock = boock;
        this.palabra_clave = palabra_clave;
    }

    public palabra_clave_libro() {
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

    public palabra_clave getPalabra_clave() {
        return palabra_clave;
    }

    public void setPalabra_clave(palabra_clave palabra_clave) {
        this.palabra_clave = palabra_clave;
    }
    
    
}
