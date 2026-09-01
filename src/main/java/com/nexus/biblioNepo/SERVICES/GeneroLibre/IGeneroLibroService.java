/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.GeneroLibre;

import com.nexus.biblioNepo.DTOS.request.GeneroLibro.GeneroDtoBasicReq;
import com.nexus.biblioNepo.DTOS.response.GenerLibro.GeneroLibroDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IGeneroLibroService {
    
    public PageResponse<GeneroLibroDtoResp>getAll(String nombre, Pageable pageable);
    
    public void  asignarGenerosToLibro(Long id_libro, List<GeneroDtoBasicReq>generos);
}
