/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Prestamos;

import com.nexus.biblioNepo.DTOS.request.Prestamos.PrestamoDtoreq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Prestamos.PrestasmosDtoResp;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IPrestamosService {

    public void prestarLibro(PrestamoDtoreq prestamoDtoreq);

    public PageResponse<PrestasmosDtoResp> getMisPrestamos(Long id_user, Pageable pageable);
    
    public void devolverLibro(Integer id_favorito);
}
