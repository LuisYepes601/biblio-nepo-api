/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.User;

import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Users.UserDetailsAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Users.UserDtoAdminResponse;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class UserAdminService implements IUserAdminService{
    
    
    private  usuarioRepository usuRepository;

    @Autowired
    public UserAdminService(usuarioRepository usuRepository) {
        this.usuRepository = usuRepository;
    }

    @Cacheable(value = "usuarios-admin")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<UserDtoAdminResponse> getAll(String num_identificacion, String email, Integer id_rol, String nombre, String primer_apellido, Pageable pageable) {

        Page<UserDtoAdminResponse>page = usuRepository.getAllAdmin(num_identificacion, email, id_rol, nombre, primer_apellido, pageable);
        
        if(page.isEmpty()){
            throw new NoDatosQueMostrarExecption("No hay usuarios que mostrar");
        }
        
        return PageResponseUtils.CreatePageReponse(page);

    }

    @Cacheable(value = "usuari-detail",key = "#id")
    @Transactional(readOnly = true)
    @Override
    public UserDetailsAdminDtoResp getDetailsById(Long id) {

        return usuRepository.getDetailsById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El usuario no existe en el sistema"));
    }
    
    
    
    
}
