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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author luis
 */
@Table(name = "palabra_clave")
@Entity
public class palabra_clave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre",
            nullable = false,
            length = 100)
    private String nombre;

    @Column(name = "descripcion",
            length = 200)
    private String descripcion;

    @OneToMany(mappedBy = "palabra_clave", fetch = FetchType.LAZY)
    private List<palabra_clave_libro> libros;

}
