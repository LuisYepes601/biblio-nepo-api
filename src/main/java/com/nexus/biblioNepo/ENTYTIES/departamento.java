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
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author luis
 */
@Table(name = "departamento",
        indexes = {
            @Index(name = "idx_departamento_nombre", columnList = "nombre"),
            @Index(name = "idx_comp_departamento_nombre_code_postal", columnList = "nombre,code_postal"),
            @Index(name = "idx_comp_departamento_nombre_code_id_pais", columnList = "nombre,code_postal,id_pais")
        })
@Entity
public class departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "nombre",
            nullable = false,
            length = 100)
    private String nombre;

    @Column(
            name = "code_postal",
            nullable = false,
            length = 50)
    private String codePostal;

    @JoinColumn(name = "id_pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pais pais;

    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<ciudad> ciudades;

}
