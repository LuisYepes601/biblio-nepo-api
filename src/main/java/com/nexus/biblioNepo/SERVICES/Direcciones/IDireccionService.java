/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Direcciones;

import com.nexus.biblioNepo.DTOS.request.DireccionDtoReq;
import com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionBasicDto;
import com.nexus.biblioNepo.ENTYTIES.direccion;

/**
 *
 * @author luis
 */
public interface IDireccionService {
    
    public direccion create(Integer id_user, DireccionDtoReq direccionDtoReq);
    
    public direccion updateById(Integer id, DireccionDtoReq direccionDtoReq);
    
    public DireccionBasicDto getByIdUser(Integer id);
}
