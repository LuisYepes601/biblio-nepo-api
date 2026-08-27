/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Tema;

import com.nexus.biblioNepo.DTOS.request.Tema.TemaDtoReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Temas.TemasDtoResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface ITemaService {

    public void createtemaLibro(Long id_libro, List<TemaDtoReq> temas);

    public void updateById(Integer id_tema, Long id_libro, TemaDtoReq temaDtoReq);
    
    public PageResponse<TemasDtoResponse>getAll(Long id_libro,Pageable pageable);
}
