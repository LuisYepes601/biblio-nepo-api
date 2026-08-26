/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Formatolibro;

import com.nexus.biblioNepo.DTOS.response.FormatoLibro.FormatoLibrODtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IFormatoLibroService {
    
    public PageResponse<FormatoLibrODtoResp>getAll(String nombre, Pageable pageable);
}
