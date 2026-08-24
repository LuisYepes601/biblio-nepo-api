/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Autors;

import java.time.LocalDate;

/**
 *
 * @author luis
 */
public class AutorDetailsBasciDto {

    private LocalDate fechaNacimiento;
    private LocalDate fechaFallecimiento;
    private String publicIdUrlFoto;
    private String urlFoto;

    public AutorDetailsBasciDto(LocalDate fechaNacimiento, LocalDate fechaFallecimiento, String publicIdUrlFoto, String urlFoto) {
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.publicIdUrlFoto = publicIdUrlFoto;
        this.urlFoto = urlFoto;
    }

    public AutorDetailsBasciDto() {
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

    public String getPublicIdUrlFoto() {
        return publicIdUrlFoto;
    }

    public void setPublicIdUrlFoto(String publicIdUrlFoto) {
        this.publicIdUrlFoto = publicIdUrlFoto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    
    
}
