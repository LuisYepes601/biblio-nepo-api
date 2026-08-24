/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.TipoIdenticicacion;

import com.nexus.biblioNepo.DTOS.request.TipoIdentificacionReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.TipoIdentificacionAdminResp.TipoIdentificacionDtoAdminResp;
import com.nexus.biblioNepo.DTOS.response.TipoIdentificacionAdminResp.TipoidentificacionDetailsAdminDto;
import com.nexus.biblioNepo.ENTYTIES.tipoidentificacion;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface ITipoIdentificacionAdminService {
    
    public tipoidentificacion create(TipoIdentificacionReq tipoIdentificacionReq);
    
    public tipoidentificacion deleteByID(Integer id);
    
    public tipoidentificacion updateByid(Integer id, TipoIdentificacionReq tipoIdentificacionReq);
    
    public PageResponse<TipoIdentificacionDtoAdminResp>getAllAdmin(String nombre, 
            Boolean isDelete, Pageable pageable );
    
    public  TipoidentificacionDetailsAdminDto getDetailsById(Integer id);
}
