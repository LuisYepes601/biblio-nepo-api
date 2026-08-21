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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author luis
 */
@Table(
        name = "autor")
@Entity
public class Autor extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            name = "nombre",
            length = 100,
            nullable = false)
    private String nombre;

    @Column(name = "segundo_nombre",
            length = 100)
    private String segundoNombre;

    @Column(name = "primer_apellido",
            nullable = false,
            length = 100)
    private String primerApellido;

    @Column(name = "seguno_apellido",
            nullable = false,
            length = 100)
    private String segundoApellido;

    @Column(
            name = "fecha_nacimiento",
            nullable = false)
    private LocalDate fechaNacimiento;

    @Column(
            name = "fecha_fallecimiento")
    private LocalDate fechaFallecimiento;

    @Column(name = "url_foto")
    private String urlFoto;

    @Column(name = "public_id_url_foto")
    private String publicIdUrlFoto;

    @Column(name = "is_fallecido",
            nullable = false)
    private Boolean isFallecido;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Boock> books;

    @JoinColumn(name = "id_pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pais nacionalidad;

    public Autor(Integer id, String nombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, LocalDate fechaFallecimiento, String urlFoto, String publicIdUrlFoto, Boolean isFallecido, List<Boock> books, Pais nacionalidad, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt, boolean isDelete, String createBy, String creatorName, String updateBy, String updateName, String deleteBy, String deleteName) {
        super(createAt, updateAt, deleteAt, isDelete, createBy, creatorName, updateBy, updateName, deleteBy, deleteName);
        this.id = id;
        this.nombre = nombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.urlFoto = urlFoto;
        this.publicIdUrlFoto = publicIdUrlFoto;
        this.isFallecido = isFallecido;
        this.books = books;
        this.nacionalidad = nacionalidad;
    }

    public Autor() {
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

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(LocalDate fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getPublicIdUrlFoto() {
        return publicIdUrlFoto;
    }

    public void setPublicIdUrlFoto(String publicIdUrlFoto) {
        this.publicIdUrlFoto = publicIdUrlFoto;
    }

    public Boolean getIsFallecido() {
        return isFallecido;
    }

    public void setIsFallecido(Boolean isFallecido) {
        this.isFallecido = isFallecido;
    }

    public List<Boock> getBooks() {
        return books;
    }

    public void setBooks(List<Boock> books) {
        this.books = books;
    }

    public Pais getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Pais nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    
}
