/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Roles;

import com.nexus.biblioNepo.DTOS.request.RolDtoAdminReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDetailsAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDtoAdminResp;
import com.nexus.biblioNepo.ENTYTIES.rol;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.rolRepository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
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
public class rolAdminService implements IRolAdmin {

    private rolRepository rolRepo;

    @Autowired
    public rolAdminService(rolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }

    @Caching(
            evict = {
                @CacheEvict(value = "roles", allEntries = true),
                @CacheEvict(value = "rol-detail", allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    @Override
    public rol create(RolDtoAdminReq rolDtoAdminReq) {

        rol rol;

        Optional<rol> optional = rolRepo.findByNombreIgnoreCase(rolDtoAdminReq.getNombre().trim());

        if (optional.isPresent()) {

            rol = optional.get();

            if (!rol.isIsDelete()) {

                throw new DatoYaExistenteException("El rol " + rol.getNombre().trim().toUpperCase() + " ya existe y se encuentra activo en el sistema");
            }
        }

        rol = new rol();

        rol.setNombre(rolDtoAdminReq.getNombre().trim().toUpperCase());

        if (rolDtoAdminReq.getDescripcion() != null) {
            rol.setDescripcion(rolDtoAdminReq.getDescripcion().trim());

        }

        AuditableUtils.create(rol, "prueba", "prueba");

        return rolRepo.save(rol);

    }

    @Caching(
            evict = {
                @CacheEvict(value = "roles", allEntries = true),
                @CacheEvict(value = "rol-detail", allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    @Override
    public rol deleteByID(Integer id) {

        rol rol;

        rol = rolRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El rol no existe en el sistema"));

        if (rol.isIsDelete()) {

            throw new DatoNoExistenteEcxeption("El rol no se encuentra activo en el sistema");
        }

        AuditableUtils.delete(rol, "prueba", "prueba");

        return rolRepo.save(rol);

    }

    @Caching(
            evict = {
                @CacheEvict(value = "roles", allEntries = true),
                @CacheEvict(value = "rol-detail", allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    @Override
    public rol updateByID(Integer id, RolDtoAdminReq rolDtoAdminReq) {

        rol rol;

        rol = rolRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El rol no existe en el sistema"));

        if (rol.isIsDelete()) {

            throw new DatoNoExistenteEcxeption("El rol no se encuentra activo en el sistema");
        }

        Optional<rol> optional = rolRepo.findByNombreIgnoreCase(rolDtoAdminReq.getNombre().trim());

        if (optional.isPresent()) {

            if (rol.getId() != optional.get().getId()) {

                rol roln = optional.get();

                if (!roln.isIsDelete()) {

                    throw new DatoYaExistenteException("Ya existe un rol con ese nombre y esta activo actualmente en el sistema");
                }
            }
        }

        rol.setNombre(rolDtoAdminReq.getNombre().trim().toUpperCase());

        if (rolDtoAdminReq.getDescripcion() != null) {

            rol.setDescripcion(rolDtoAdminReq.getDescripcion().trim());
        }

        AuditableUtils.update(rol, "prueba", "prueba");

        return rolRepo.save(rol);

    }

    @Cacheable(value = "roles")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<RolDtoAdminResp> getAllAdmin(String nombre, Boolean is_delete, Pageable pageable) {

        Page<RolDtoAdminResp> page = rolRepo.getAllAdmin(nombre, is_delete, pageable);

        if (page.isEmpty()) {

            throw new NoDatosQueMostrarExecption("No hay roles que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);
    }

    @Cacheable(value = "rol-detail", key = "#id")
    @Transactional(readOnly = true)
    @Override
    public RolDetailsAdminDtoResp getDetailByID(Integer id) {

        return rolRepo.getDetailsbyId(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El rol no existe en el sistema"));

    }

}
