/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Perfil;

import com.nexus.biblioNepo.DTOS.response.Cloudinary.CloudinaryUploadResponse;
import com.nexus.biblioNepo.DTOS.response.Perfil.UsuarioPerfilDtoResp;
import com.nexus.biblioNepo.ENTYTIES.usuario;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import com.nexus.biblioNepo.SERVICES.cloudinary.cloudinaryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
@Service
public class PerfilService implements IPerfilService {

    private usuarioRepository usRepo;
    private cloudinaryServices cdnService;

    @Autowired
    public PerfilService(usuarioRepository usRepo, cloudinaryServices cdnService) {
        this.usRepo = usRepo;
        this.cdnService = cdnService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateFotoPerfil(Long id_user, MultipartFile file) {

        usuario us = usRepo.findById(id_user)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("No existe el usuario en el sistema."));

        cdnService.deleteFile(us.getPublicIdUrlFotoPerfil());

        CloudinaryUploadResponse cloudinaryUploadResponse = cdnService.uploadFotoPerfil(file, us.getNombre(), file.getOriginalFilename());

        us.setUrlFotoPerfil(cloudinaryUploadResponse.getSecureUrl());
        us.setPublicIdUrlFotoPerfil(cloudinaryUploadResponse.getPublicId());

    }

    @Cacheable(value = "user-perfil")
    @Transactional(readOnly = true)
    @Override
    public UsuarioPerfilDtoResp getDatosBasicos(Long id_user) {

        return usRepo.getDatosBaicosPerfil(id_user)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El usuario no existe en el sistema"));

    }

}
