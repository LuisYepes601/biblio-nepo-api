/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Formatolibro;

import com.nexus.biblioNepo.DTOS.request.Formatolibro.FormatolibroDtoReq;
import com.nexus.biblioNepo.DTOS.response.FormatoLibro.FormatoLibroAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.ENTYTIES.formatoLibro;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IFormatoLibroAdminService {
    
    public formatoLibro create(FormatolibroDtoReq formatolibroDtoReq);
    
    public formatoLibro update(Integer id, FormatolibroDtoReq formatolibroDtoReq);
    
    public formatoLibro deleteById(Integer id);
    
    public PageResponse<FormatoLibroAdminDtoResp>getAll(String nombre, Boolean is_delete, Pageable pageable);
    
}
