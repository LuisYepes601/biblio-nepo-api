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
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
public class libro_genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "id_genero_libro")
    @ManyToOne(fetch = FetchType.LAZY)
    private generoLibro generoLibro;

    @JoinColumn(name = "id_libro")
    @ManyToOne(fetch = FetchType.LAZY)
    private Boock boock;
}
