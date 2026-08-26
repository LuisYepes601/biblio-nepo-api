/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Ciudad;

import com.nexus.biblioNepo.DTOS.response.City.CityDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.ciudadRepository;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class CiudadService implements ICiudadService {

    private ciudadRepository ciudadRepo;

    @Autowired
    public CiudadService(ciudadRepository ciudadRepo) {
        this.ciudadRepo = ciudadRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<CityDtoResp> getAll(String nombre, Integer id_dep, Pageable pageable) {

        Page<CityDtoResp> page = ciudadRepo.getAll(nombre, id_dep, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay ciudades que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);
    }

}
