/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.GeneroLibre;

import com.nexus.biblioNepo.DTOS.request.GeneroLibro.GenerolibroDtoReq;
import com.nexus.biblioNepo.DTOS.response.GenerLibro.GeneroLibroAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.ENTYTIES.generoLibro;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IGeneroLibroServiceAdmin {

    public generoLibro create(GenerolibroDtoReq generolibroDtoReq);
    
    public generoLibro updateById(Integer id, GenerolibroDtoReq generolibroDtoReq);
    
    public generoLibro deleteByID(Integer id);
    
    public PageResponse<GeneroLibroAdminDtoResp>getAll(String nombre, Boolean isDelete, Pageable pageable);
}
