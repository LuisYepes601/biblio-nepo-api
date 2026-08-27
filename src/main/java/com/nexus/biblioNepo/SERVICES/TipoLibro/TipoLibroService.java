/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.TipoLibro;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.TipoLibro.TipoLibroDtoResp;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.tipolibroRepository;
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
public class TipoLibroService implements ITipoLibroService {

    private tipolibroRepository tipoLiRepository;

    @Autowired
    public TipoLibroService(tipolibroRepository tipoLiRepository) {
        this.tipoLiRepository = tipoLiRepository;
    }

    @Cacheable(value = "tipo-libros")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<TipoLibroDtoResp> getAll(Pageable pageable, String nombre) {

        Page<TipoLibroDtoResp> page = tipoLiRepository.getAll(nombre, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay tipos de libros que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

}
