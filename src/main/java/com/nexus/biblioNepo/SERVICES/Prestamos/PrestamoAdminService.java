/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Prestamos;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Prestamos.PrestamosAdminDtoResp;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.prestamo_repository;
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
public class PrestamoAdminService implements IPrestamosAdminService {

    private prestamo_repository prestRepo;

    @Autowired
    public PrestamoAdminService(prestamo_repository prestRepo) {
        this.prestRepo = prestRepo;
    }

    @Cacheable(value = "prestamos_admin")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<PrestamosAdminDtoResp> getPrestamos(String email, Pageable pageable) {

        Page<PrestamosAdminDtoResp> page = prestRepo.getPrestamos(email, pageable);

        if (page.isEmpty()) {

            throw new NoDatosQueMostrarExecption("No hay prestamos que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

}
