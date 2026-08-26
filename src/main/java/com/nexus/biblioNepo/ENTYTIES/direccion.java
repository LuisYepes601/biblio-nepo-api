/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.ENTYTIES;

import com.nexus.biblioNepo.AUDITORIA.Auditoria;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
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
import java.time.LocalDateTime;

/**
 *
 * @author luis
 */
@Table(name = "direccion")
@Entity
public class direccion extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "barrio",
            length = 100)
    private String barrio;

    private String complemento;

    @Column(
            name = "mas_detalles")
    private String masDetalles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais", nullable = false)
    private Pais pais;

    @OneToOne(mappedBy = "direccion", fetch = FetchType.LAZY)
    private sede sede;

    @OneToOne(mappedBy = "direccion", fetch = FetchType.LAZY)
    private usuario usuario;

    @JoinColumn(name = "id_departamento", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private departamento departamento;

    @JoinColumn(name = "id_ciudad", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ciudad ciudad;

    public direccion(Integer id, String barrio, String complemento, String masDetalles, Pais pais, sede sede, usuario usuario, departamento departamento, ciudad ciudad, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.barrio = barrio;
        this.complemento = complemento;
        this.masDetalles = masDetalles;
        this.pais = pais;
        this.sede = sede;
        this.usuario = usuario;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    public direccion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getMasDetalles() {
        return masDetalles;
    }

    public void setMasDetalles(String masDetalles) {
        this.masDetalles = masDetalles;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public sede getSede() {
        return sede;
    }

    public void setSede(sede sede) {
        this.sede = sede;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

    public departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(departamento departamento) {
        this.departamento = departamento;
    }

    public ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(ciudad ciudad) {
        this.ciudad = ciudad;
    }

}
