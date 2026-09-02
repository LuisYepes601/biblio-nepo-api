/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Libro;

/**
 *
 * @author luis
 */
public class DetalleLibroBasicRespDto {

    private String portadaUrl;

    private String archivoUrl;

    private String descripcion;

    private String paisPublicacion;

    private String idioma;

    private String formatoLibro;

    public DetalleLibroBasicRespDto(String portadaUrl, String archivoUrl, String descripcion, String paisPublicacion, String idioma, String formatoLibro) {
        this.portadaUrl = portadaUrl;
        this.archivoUrl = archivoUrl;
        this.descripcion = descripcion;
        this.paisPublicacion = paisPublicacion;
        this.idioma = idioma;
        this.formatoLibro = formatoLibro;
    }

    public DetalleLibroBasicRespDto() {
    }

    public String getPortadaUrl() {
        return portadaUrl;
    }

    public void setPortadaUrl(String portadaUrl) {
        this.portadaUrl = portadaUrl;
    }

    public String getArchivoUrl() {
        return archivoUrl;
    }

    public void setArchivoUrl(String archivoUrl) {
        this.archivoUrl = archivoUrl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPaisPublicacion() {
        return paisPublicacion;
    }

    public void setPaisPublicacion(String paisPublicacion) {
        this.paisPublicacion = paisPublicacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getFormatoLibro() {
        return formatoLibro;
    }

    public void setFormatoLibro(String formatoLibro) {
        this.formatoLibro = formatoLibro;
    }
    
    
}
