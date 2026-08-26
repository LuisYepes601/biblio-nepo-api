/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Pais;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Pais.PaisDtoResp;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.paisRepository;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class PaisService implements IPaisservice {

    private paisRepository paisRepo;

    @Autowired
    public PaisService(paisRepository paisRepo) {
        this.paisRepo = paisRepo;
    }

    @CacheEvict(value = "paises-basicos", allEntries = true)
    @Transactional(readOnly = true)
    @Override
    public PageResponse<PaisDtoResp> getAll(Pageable pageable) {

        Page<PaisDtoResp> page = paisRepo.getAll(pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay paises que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);
    }

}
