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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;

/**
 *
 * @author luis
 */
@Table(name = "sede")
@Entity
public class sede extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "nombre",
            nullable = false,
            length = 100)
    private String nombre;

    @Column(
            name = "descripcion",
            length = 500)
    private String descripcion;

    @Column(
            name = "telefono",
            nullable = false,
            length = 20)
    private String telefono;

    @Column(
            name = "correo",
            nullable = false,
            length = 150
    )
    private String correo;

    @Column(
            name = "horario_apertura",
            nullable = false)
    private LocalTime horarioApertura;

    @Column(
            name = "horario_cierre",
            nullable = false)
    private LocalTime horarioCierre;

    @Column(
            name = "lunes_a_sabado",
            nullable = false)
    private Boolean lunesASabado;

    @Column(name = "is_open",
            nullable = false)
    private Boolean isOpen;

    @JoinColumn(name = "id_direccion", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private direccion direccion;

    @JoinColumn(name = "id_biblioteca", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private biblioteca biblioteca;

}
