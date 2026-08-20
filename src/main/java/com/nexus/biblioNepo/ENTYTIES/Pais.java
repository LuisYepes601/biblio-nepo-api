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
@Table(name = "pais")
@Entity
public class Pais extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "nombre",
            unique = true,
            nullable = false,
            length = 100)
    private String nombre;

    @Column(
            name = "iso_3",
            nullable = false,
            unique = true)
    private String iso_3;

    @OneToMany(mappedBy = "nacionalidad", fetch = FetchType.LAZY)
    private List<Autor> autores;

    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY)
    private List<departamento> departamentos;
    
    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY)
    private List<direccion>direcciones;

}
