/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.TipoLibro;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.TipoLibro.TipoLibroDtoResp;
import com.nexus.biblioNepo.ENTYTIES.tipoLibro;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface ITipoLibroService {
    
    public PageResponse<TipoLibroDtoResp>getAll(Pageable pageable, String nombre);
}
