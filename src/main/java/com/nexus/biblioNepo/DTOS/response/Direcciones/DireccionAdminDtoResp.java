/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Direcciones;

import java.time.LocalDateTime;

/**
 *
 * @author luis
 */
public class DireccionAdminDtoResp extends DireccionBasicDto {

    private Long id_user;

    private String nombre_user;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public DireccionAdminDtoResp(Long id_user, String nombre_user, LocalDateTime createAt, LocalDateTime updateAt, Integer id, String barrio, String complemento, String masDetalles, Integer idPais, String pais, Integer idDepartamento, String departamento, Integer idCiudad, String ciudad) {
        super(id, barrio, complemento, masDetalles, idPais, pais, idDepartamento, departamento, idCiudad, ciudad);
        this.id_user = id_user;
        this.nombre_user = nombre_user;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public DireccionAdminDtoResp() {
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getNombre_user() {
        return nombre_user;
    }

    public void setNombre_user(String nombre_user) {
        this.nombre_user = nombre_user;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

}
