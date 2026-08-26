/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Idiomas;

import com.nexus.biblioNepo.DTOS.response.Idioms.IdiomRespDto;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.idiomRepository;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class IdiomasService implements IIdiomasService {

    private idiomRepository idiomRepo;

    @Autowired
    public IdiomasService(idiomRepository idiomRepo) {
        this.idiomRepo = idiomRepo;
    }

    @Cacheable(value = "idiomas-basic")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<IdiomRespDto> getAll(String nombre, Pageable pageable) {

        Page<IdiomRespDto> page = idiomRepo.getAll(nombre, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay idiomas que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

}
