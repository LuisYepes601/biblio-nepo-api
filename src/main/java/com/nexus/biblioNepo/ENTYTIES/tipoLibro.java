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
import java.util.List;

/**
 *
 * @author luis
 */
@Table(name = "tipo_libro")
@Entity
public class tipoLibro extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre",
            nullable = false,
            unique = true,
            length = 100)
    private String nombre;

    @Column(
            name = "descripcion",
            length = 200)
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoLibro")
    private List<Boock> boocks;

    public tipoLibro(Integer id, String nombre, String descripcion, List<Boock> boocks) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.boocks = boocks;
    }

    public tipoLibro() {
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

    public List<Boock> getBoocks() {
        return boocks;
    }

    public void setBoocks(List<Boock> boocks) {
        this.boocks = boocks;
    }
    
    
}
