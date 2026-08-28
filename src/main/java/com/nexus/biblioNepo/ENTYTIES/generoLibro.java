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
        name = "genero_libro",
        indexes = {
            @Index(name = "idx_genero_libro_nombre", columnList = "nombre")
        })
@Entity
public class generoLibro extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "generoLibro", fetch = FetchType.LAZY)
    private List<libro_genero> libros_generos;

    public generoLibro(Integer id, String nombre, String descripcion, List<libro_genero> libros_generos, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.libros_generos = libros_generos;
    }

   
    public generoLibro() {
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

    public List<libro_genero> getLibros_generos() {
        return libros_generos;
    }

    public void setLibros_generos(List<libro_genero> libros_generos) {
        this.libros_generos = libros_generos;
    }

}
