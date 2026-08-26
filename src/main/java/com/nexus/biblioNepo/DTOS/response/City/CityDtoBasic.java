/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.City;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author luis
 */
public class CityDtoBasic {

    private Integer id;
    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String name;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer departmentId;

    public CityDtoBasic(Integer id, String name, Integer departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
    }

   
    public CityDtoBasic() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
