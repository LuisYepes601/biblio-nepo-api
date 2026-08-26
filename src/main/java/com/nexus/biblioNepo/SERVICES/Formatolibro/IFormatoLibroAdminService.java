/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Formatolibro;

import com.nexus.biblioNepo.DTOS.request.Formatolibro.FormatolibroDtoReq;
import com.nexus.biblioNepo.ENTYTIES.formatoLibro;

/**
 *
 * @author luis
 */
public interface IFormatoLibroAdminService {
    
    public formatoLibro create(FormatolibroDtoReq formatolibroDtoReq);
    
    public formatoLibro update(Integer id, FormatolibroDtoReq formatolibroDtoReq);
    
    public formatoLibro deleteById(Integer id);
    
    
}
