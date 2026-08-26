/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Departamentos;

import com.nexus.biblioNepo.DTOS.response.Departamentos.DepartamentoBasicDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.departamentoRepository;
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
public class DepartamentoService implements IDepartamentoService {

    private departamentoRepository depRepo;

    @Autowired
    public DepartamentoService(departamentoRepository depRepo) {
        this.depRepo = depRepo;
    }

    @Cacheable(value = "departamentos")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<DepartamentoBasicDtoResp> getAll(String name, Integer id_pais, Pageable pageable) {

        Page<DepartamentoBasicDtoResp> page = depRepo.getAllBasic(name, id_pais, pageable);
        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay departamentos que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

}
