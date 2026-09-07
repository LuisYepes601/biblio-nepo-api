/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Perfil;

import com.nexus.biblioNepo.DTOS.response.Perfil.UsuarioPerfilDtoResp;
import com.nexus.biblioNepo.DTOS.response.Users.UserDtoAdminResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
public interface IPerfilService {
    
    public void updateFotoPerfil(Long id_user, MultipartFile file);
    
    public UsuarioPerfilDtoResp getDatosBasicos(Long id_user);
}
