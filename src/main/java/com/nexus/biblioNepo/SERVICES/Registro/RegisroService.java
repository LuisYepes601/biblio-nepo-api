/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Registro;

import com.nexus.biblioNepo.DTOS.request.DireccionDtoReq;
import com.nexus.biblioNepo.DTOS.request.Usuarios.UsuarioBasicoDtoReq;
import com.nexus.biblioNepo.DTOS.response.Cloudinary.CloudinaryUploadResponse;
import com.nexus.biblioNepo.ENTYTIES.usuario;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.REPOSITORIES.rolRepository;
import com.nexus.biblioNepo.REPOSITORIES.tipoIdentificacionRepository;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import com.nexus.biblioNepo.SERVICES.Direcciones.IDireccionService;
import com.nexus.biblioNepo.SERVICES.PasswordEncoder.PasswordEncoderService;
import com.nexus.biblioNepo.SERVICES.cloudinary.ICloudinaryService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
@Service
public class RegisroService implements IRegistroService {

    private usuarioRepository usuarioRepo;
    private rolRepository rolRepo;
    private tipoIdentificacionRepository tipoIdentificacionRepo;
    private ICloudinaryService cloudinaryService;
    private PasswordEncoderService passwordEncodeService;
    private IDireccionService direccionService;

    @Autowired
    public RegisroService(usuarioRepository usuarioRepo, rolRepository rolRepo, tipoIdentificacionRepository tipoIdentificacionRepo, ICloudinaryService cloudinaryService, PasswordEncoderService passwordEncodeService, IDireccionService direccionService) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.tipoIdentificacionRepo = tipoIdentificacionRepo;
        this.cloudinaryService = cloudinaryService;
        this.passwordEncodeService = passwordEncodeService;
        this.direccionService = direccionService;
    }

    @Override
    public usuario register(UsuarioBasicoDtoReq usuarioBasicoDtoReq, MultipartFile fotoPerfil) {

        usuario us;

        Optional<usuario> optional = usuarioRepo.findByEmail(usuarioBasicoDtoReq.getEmail().toLowerCase().trim());

        if (optional.isPresent()) {
            throw new DatoYaExistenteException("Ya existe una cuenta con este email");

        }
        us = new usuario();

        us.setNombre(usuarioBasicoDtoReq.getNombre().trim());
        if (usuarioBasicoDtoReq.getSegundoNombre() != null) {
            us.setSegundoNombre(usuarioBasicoDtoReq.getSegundoNombre().trim());
        }

        us.setPrimerApellido(usuarioBasicoDtoReq.getPrimerApellido().trim());
        us.setSegundoApellido(usuarioBasicoDtoReq.getSegundoApellido().trim());

        us.setEmail(usuarioBasicoDtoReq.getEmail().trim());
        us.setFechaNacimiento(usuarioBasicoDtoReq.getFechaNacimiento());

        if (usuarioBasicoDtoReq.getId_tipo_identificacion() != null) {

            us.setTipoIdentificacion(tipoIdentificacionRepo.
                    findById(usuarioBasicoDtoReq.getId_tipo_identificacion())
                    .orElseThrow(()
                            -> new DatoNoExistenteEcxeption("No existe ese tipo de identificación en el sistema")));
            us.setNumeroIdentificacion(usuarioBasicoDtoReq.getNumeroIdentificacion());
        }

        if (usuarioBasicoDtoReq.getId_rol() != null) {
            us.setRol(rolRepo.findById(usuarioBasicoDtoReq.getId_rol())
                    .orElseThrow(() -> new DatoNoExistenteEcxeption("No existe el rol en el sistema")));
        }

        if (fotoPerfil != null) {

            CloudinaryUploadResponse cloudinaryUploadResponse
                    = cloudinaryService.uploadFotoPerfil(
                            fotoPerfil,
                            usuarioBasicoDtoReq.getNombre().trim(),
                            fotoPerfil.getOriginalFilename());

            us.setUrlFotoPerfil(cloudinaryUploadResponse.getSecureUrl());
            us.setPublicIdUrlFotoPerfil(cloudinaryUploadResponse.getPublicId());
        }

        us.setPassword(passwordEncodeService.encriptarPassword(usuarioBasicoDtoReq.getPassword().trim()));

        return usuarioRepo.save(us);

    }

    @CacheEvict(value = "direcciones")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public usuario registerDireccion(Integer id_user, DireccionDtoReq direccionDtoReq) {

        usuario user = usuarioRepo.findById(id_user)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El usuario no existe en el sistema"));

        user.setDireccion(direccionService.create(id_user, direccionDtoReq));

        return usuarioRepo.save(user);
    }

}
