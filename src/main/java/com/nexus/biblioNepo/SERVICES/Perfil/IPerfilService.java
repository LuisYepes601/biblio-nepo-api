/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Perfil;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
public interface IPerfilService {
    
    public void updateFotoPerfil(Long id_user, MultipartFile file);
}
