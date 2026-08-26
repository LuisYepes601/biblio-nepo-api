/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Formatolibro;

import com.nexus.biblioNepo.DTOS.response.FormatoLibro.FormatoLibrODtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.formatoLibroRepository;
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
public class FormatoLibroService implements IFormatoLibroService {

    private formatoLibroRepository formLibroRepository;

    @Autowired
    public FormatoLibroService(formatoLibroRepository formLibroRepository) {
        this.formLibroRepository = formLibroRepository;
    }

    @Cacheable(value = "formato-libros-basic")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<FormatoLibrODtoResp> getAll(String nombre, Pageable pageable) {

        Page<FormatoLibrODtoResp> page = formLibroRepository.getAll(nombre, pageable);

        if (page.isEmpty()) {

            throw new NoDatosQueMostrarExecption("No hay formatos que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

}
