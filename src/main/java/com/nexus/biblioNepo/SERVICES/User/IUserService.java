/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.User;

import com.nexus.biblioNepo.DTOS.request.Usuarios.UsuarioBasicoDtoReq;
import com.nexus.biblioNepo.ENTYTIES.usuario;

/**
 *
 * @author luis
 */
public interface IUserService {

    public usuario updateById(Long id, UsuarioBasicoDtoReq usuarioBasicoDtoReq);
}
