/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Direcciones;

import com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionAdminDtoResp;
import java.util.Optional;

/**
 *
 * @author luis
 */
public interface IDireccionServiceADMIN {
    
    public DireccionAdminDtoResp getByIdUser(Integer id);
}
