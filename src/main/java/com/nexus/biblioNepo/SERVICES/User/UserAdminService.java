/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.User;

import com.nexus.biblioNepo.DTOS.request.Usuarios.UsuarioBasicoDtoReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Users.UserDetailsAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Users.UserDtoAdminResponse;
import com.nexus.biblioNepo.ENTYTIES.usuario;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.rolRepository;
import com.nexus.biblioNepo.REPOSITORIES.tipoIdentificacionRepository;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class UserAdminService implements IUserAdminService {

    private usuarioRepository usuRepository;
    private tipoIdentificacionRepository tipoIdenRepo;
    private rolRepository rolRepo;

    @Autowired
    public UserAdminService(usuarioRepository usuRepository, tipoIdentificacionRepository tipoIdenRepo, rolRepository rolRepo) {
        this.usuRepository = usuRepository;
        this.tipoIdenRepo = tipoIdenRepo;
        this.rolRepo = rolRepo;
    }

    @Cacheable(value = "usuarios-admin")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<UserDtoAdminResponse> getAll(String num_identificacion, String email, Integer id_rol, String nombre, String primer_apellido, Pageable pageable) {

        Page<UserDtoAdminResponse> page = usuRepository.getAllAdmin(num_identificacion, email, id_rol, nombre, primer_apellido, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay usuarios que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

    @Cacheable(value = "usuario-detail", key = "#id")
    @Transactional(readOnly = true)
    @Override
    public UserDetailsAdminDtoResp getDetailsById(Long id) {

        return usuRepository.getDetailsById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El usuario no existe en el sistema"));
    }

}
