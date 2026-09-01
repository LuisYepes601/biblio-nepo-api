/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.Categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author luis
 */
public class CategoriaAsignarDto {

    @Schema(name = "id",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @Positive(message = "El id de la categoria debe de ser positivo")
    @NotNull(message = "Debe de selecionar al menos una categoria")
    private Integer id;

    public CategoriaAsignarDto(Integer id) {
        this.id = id;
    }

    public CategoriaAsignarDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
