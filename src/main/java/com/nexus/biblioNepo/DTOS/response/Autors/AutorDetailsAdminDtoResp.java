/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Autors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author luis
 */
public class AutorDetailsAdminDtoResp {

    private LocalDate fechaNacimiento;
    private LocalDate fechaFallecimiento;
    private String publicIdUrlFoto;
    private String urlFoto;

    // Auditoría
    private LocalDateTime createAt;
    private String createBy;
    private String creatorName;

    private LocalDateTime updateAt;
    private String updateBy;
    private String updateName;

    private LocalDateTime deleteAt;
    private String deleteBy;
    private String deleteName;

    public AutorDetailsAdminDtoResp(LocalDate fechaNacimiento, LocalDate fechaFallecimiento, String publicIdUrlFoto, String urlFoto, LocalDateTime createAt, String createBy, String creatorName, LocalDateTime updateAt, String updateBy, String updateName, LocalDateTime deleteAt, String deleteBy, String deleteName) {
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.publicIdUrlFoto = publicIdUrlFoto;
        this.urlFoto = urlFoto;
        this.createAt = createAt;
        this.createBy = createBy;
        this.creatorName = creatorName;
        this.updateAt = updateAt;
        this.updateBy = updateBy;
        this.updateName = updateName;
        this.deleteAt = deleteAt;
        this.deleteBy = deleteBy;
        this.deleteName = deleteName;
    }

    public AutorDetailsAdminDtoResp() {
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public LocalDateTime getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDateTime deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    public String getDeleteName() {
        return deleteName;
    }

    public void setDeleteName(String deleteName) {
        this.deleteName = deleteName;
    }
    
    

}
