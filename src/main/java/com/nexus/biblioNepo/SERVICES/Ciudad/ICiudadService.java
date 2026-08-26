/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Ciudad;

import com.nexus.biblioNepo.DTOS.response.City.CityDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface ICiudadService {
    
    public PageResponse<CityDtoResp>getAll(String nombre, Integer id_dep, Pageable pageable);
}
