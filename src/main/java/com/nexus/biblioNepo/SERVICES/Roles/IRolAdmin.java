/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Roles;

import com.nexus.biblioNepo.DTOS.request.RolDtoAdminReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDetailsAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDtoAdminResp;
import com.nexus.biblioNepo.ENTYTIES.rol;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IRolAdmin {

    public rol create(RolDtoAdminReq rolDtoAdminReq);

    public rol deleteByID(Integer id);

    public rol updateByID(Integer id, RolDtoAdminReq rolDtoAdminReq);

    public PageResponse<RolDtoAdminResp> getAllAdmin(String nombre, Boolean is_delete, Pageable pageable);
    
    public RolDetailsAdminDtoResp getDetailByID(Integer id);

}
