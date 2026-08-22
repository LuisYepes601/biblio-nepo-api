/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Roles;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDtoBasic;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IRolService {

    public PageResponse<RolDtoBasic> getAll(Pageable pageable);
}
