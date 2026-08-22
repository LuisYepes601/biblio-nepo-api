/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Roles;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDtoBasic;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.rolRepository;
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
public class rolService implements IRolService {

    private rolRepository rolRepo;

    @Autowired
    public rolService(rolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }

    @Cacheable(value = "roles-basic")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<RolDtoBasic> getAll(Pageable pageable) {

        Page<RolDtoBasic> page = rolRepo.getAll(pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay roles que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

}
