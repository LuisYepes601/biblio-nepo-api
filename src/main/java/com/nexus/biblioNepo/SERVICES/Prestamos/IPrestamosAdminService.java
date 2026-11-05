/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Prestamos;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Prestamos.PrestamosAdminDtoResp;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IPrestamosAdminService {

    public PageResponse<PrestamosAdminDtoResp> getPrestamos(String email, Pageable pageable);
}
