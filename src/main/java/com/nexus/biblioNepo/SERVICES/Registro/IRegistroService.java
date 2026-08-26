/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Registro;

import com.nexus.biblioNepo.DTOS.request.DireccionDtoReq;
import com.nexus.biblioNepo.DTOS.request.Usuarios.UsuarioBasicoDtoReq;
import com.nexus.biblioNepo.ENTYTIES.usuario;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
public interface IRegistroService {

    public usuario register(UsuarioBasicoDtoReq usuarioBasicoDtoReq, MultipartFile fotoPerfil);

    public usuario registerDireccion(Integer id_user, DireccionDtoReq direccionDtoReq);
}
