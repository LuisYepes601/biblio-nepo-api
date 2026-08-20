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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author luis
 */
@Table(name = "prestamo",
        indexes = {
            @Index(name = "idx_prestamo_id_usuario", columnList = "id_usuario")
        })
@Entity
public class prestamo extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "id_usuario", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private usuario usuario;

    @JoinColumn(name = "id_boock", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Boock boock;

    @Column(
            name = "fecha_prestamo",
            nullable = false)
    private LocalDate fechaPrestamo;

    @Column(
            name = "fecha_vencimiento",
            nullable = false)
    private LocalDate fecha_venciimiento;

    @Column(
            name = "hora_prestamo",
            nullable = false)
    private LocalTime hora_prestamo;

    @Column(
            name = "hora_vencimiento",
            nullable = false)
    private LocalTime hora_vencimiento;

    public prestamo(Integer id, usuario usuario, Boock boock, LocalDate fechaPrestamo, LocalDate fecha_venciimiento, LocalTime hora_prestamo, LocalTime hora_vencimiento, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.usuario = usuario;
        this.boock = boock;
        this.fechaPrestamo = fechaPrestamo;
        this.fecha_venciimiento = fecha_venciimiento;
        this.hora_prestamo = hora_prestamo;
        this.hora_vencimiento = hora_vencimiento;
    }

    public prestamo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

    public Boock getBoock() {
        return boock;
    }

    public void setBoock(Boock boock) {
        this.boock = boock;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFecha_venciimiento() {
        return fecha_venciimiento;
    }

    public void setFecha_venciimiento(LocalDate fecha_venciimiento) {
        this.fecha_venciimiento = fecha_venciimiento;
    }

    public LocalTime getHora_prestamo() {
        return hora_prestamo;
    }

    public void setHora_prestamo(LocalTime hora_prestamo) {
        this.hora_prestamo = hora_prestamo;
    }

    public LocalTime getHora_vencimiento() {
        return hora_vencimiento;
    }

    public void setHora_vencimiento(LocalTime hora_vencimiento) {
        this.hora_vencimiento = hora_vencimiento;
    }
    
    
}
