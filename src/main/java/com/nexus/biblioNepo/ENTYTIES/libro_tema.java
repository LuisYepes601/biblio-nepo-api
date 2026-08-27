/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.ENTYTIES;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author luis
 */
@Table(name = "libro_tema")
@Entity
public class libro_tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "id_tema", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private tema tema;

    @JoinColumn(name = "id_libro", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Boock boock;

    public libro_tema(Integer id, tema tema, Boock boock) {
        this.id = id;
        this.tema = tema;
        this.boock = boock;
    }

    public libro_tema() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public tema getTema() {
        return tema;
    }

    public void setTema(tema tema) {
        this.tema = tema;
    }

    public Boock getBoock() {
        return boock;
    }

    public void setBoock(Boock boock) {
        this.boock = boock;
    }
    
    
}
