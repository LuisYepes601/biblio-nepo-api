/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Departamentos;

import com.nexus.biblioNepo.DTOS.response.Departamentos.DepartamentoBasicDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IDepartamentoService {

    public PageResponse<DepartamentoBasicDtoResp> getAll(String name, Integer id_pais, Pageable pageable);
}
