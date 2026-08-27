/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.TipoLibro;

import com.nexus.biblioNepo.DTOS.request.TipoLibro.TipoLibroDtoReq;
import com.nexus.biblioNepo.ENTYTIES.tipoLibro;

/**
 *
 * @author luis
 */
public interface ITipoLibroAdminService {
    
    public tipoLibro create(TipoLibroDtoReq tipoLibroDtoReq);
    
    public tipoLibro updateById(Integer id, TipoLibroDtoReq tipoLibroDtoReq);
}
