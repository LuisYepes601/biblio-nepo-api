/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.GeneroLibre;

import com.nexus.biblioNepo.DTOS.response.GenerLibro.GeneroLibroDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.generoLibroRepository;
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
public class GeneroLibroService implements IGeneroLibroService {

    private generoLibroRepository genLibroRepository;

    @Autowired
    public GeneroLibroService(generoLibroRepository genLibroRepository) {
        this.genLibroRepository = genLibroRepository;
    }

    @Cacheable(value = "genero-libros")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<GeneroLibroDtoResp> getAll(String nombre, Pageable pageable) {

        Page<GeneroLibroDtoResp> page = genLibroRepository.getAll(nombre, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay generos que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

}
