/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.User;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Users.UserDtoAdminResponse;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IUserAdminService {

    public PageResponse<UserDtoAdminResponse> getAll(String num_identificacion,
            String email,
            Integer id_rol,
            String nombre,
            String primer_apellido, 
            Pageable pageable);
}
