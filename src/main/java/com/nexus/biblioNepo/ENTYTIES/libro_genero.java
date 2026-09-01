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
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author luis
 */
@Table(
        name = "libro_genero",
        indexes = {
            @Index(name = "idx_libro_genero_id_genero_libro", columnList = "id_genero_libro"),
            @Index(name = "idx_libro_genero_id_libro", columnList = "id_libro"),
            @Index(name = "idx_comp_libro_genero_id_libro_id_libro", columnList = "id_genero_libro,id_libro")
        })
@Entity
public class libro_genero extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "id_genero_libro")
    @ManyToOne(fetch = FetchType.LAZY)
private generoLibro generoLibro;

    @JoinColumn(name = "id_libro")
    @ManyToOne(fetch = FetchType.LAZY)
    private Boock boock;

    public libro_genero(Integer id, generoLibro generoLibro, Boock boock, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.generoLibro = generoLibro;
        this.boock = boock;
    }

  
    public libro_genero() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public generoLibro getGeneroLibro() {
        return generoLibro;
    }

    public void setGeneroLibro(generoLibro generoLibro) {
        this.generoLibro = generoLibro;
    }

    public Boock getBoock() {
        return boock;
    }

    public void setBoock(Boock boock) {
        this.boock = boock;
    }
    
    
}
