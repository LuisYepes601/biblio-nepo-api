/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.User;

import com.nexus.biblioNepo.DTOS.request.Usuarios.UsuarioBasicoDtoReq;
import com.nexus.biblioNepo.ENTYTIES.usuario;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.REPOSITORIES.rolRepository;
import com.nexus.biblioNepo.REPOSITORIES.tipoIdentificacionRepository;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import java.util.Optional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class UserService implements IUserService {

    private usuarioRepository usuRepository;
    private tipoIdentificacionRepository tipoIdenRepo;
    private rolRepository rolRepo;

    public UserService(usuarioRepository usuRepository, tipoIdentificacionRepository tipoIdenRepo, rolRepository rolRepo) {
        this.usuRepository = usuRepository;
        this.tipoIdenRepo = tipoIdenRepo;
        this.rolRepo = rolRepo;
    }

    @Caching(
            evict = {
                @CacheEvict(value = "usuarios-admin", allEntries = true)
            })
    @Transactional(rollbackFor = Exception.class)
    @Override
    public usuario updateById(Long id, UsuarioBasicoDtoReq usuarioBasicoDtoReq) {

        usuario us = usuRepository.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El usuario no existe en el sistema"));

        Optional<usuario> existeEmail = usuRepository.findByEmail(usuarioBasicoDtoReq.getEmail().trim());

        if (existeEmail.isPresent()) {

            if (existeEmail.get().getId() != us.getId()) {
                throw new DatoYaExistenteException("El email ya se encuentra activo en otra cuenta.");
            }

        }

        us.setEmail(usuarioBasicoDtoReq.getEmail().trim());
        us.setNombre(usuarioBasicoDtoReq.getNombre().trim());

        if (usuarioBasicoDtoReq.getNombre() != null) {
            us.setSegundoNombre(usuarioBasicoDtoReq.getSegundoNombre().trim());
        }

        us.setPrimerApellido(us.getPrimerApellido().trim());

        if (usuarioBasicoDtoReq.getSegundoApellido() != null) {
            us.setSegundoApellido(usuarioBasicoDtoReq.getSegundoApellido().trim());
        }

        us.setFechaNacimiento(us.getFechaNacimiento());

        us.setTipoIdentificacion(tipoIdenRepo.findById(usuarioBasicoDtoReq.getId_tipo_identificacion())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El tipo de identificacion no existe en el sistema")));
        us.setNumeroIdentificacion(us.getNumeroIdentificacion().trim());

        us.setRol(rolRepo.findById(usuarioBasicoDtoReq.getId_rol())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El rol no existe en el sistema")));

        return usuRepository.save(us);

    }

}
