/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Idiomas;

import com.nexus.biblioNepo.DTOS.response.Idioms.IdiomRespDto;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IIdiomasService {
    
    public PageResponse<IdiomRespDto>getAll(String nombre, Pageable pageable);
}
