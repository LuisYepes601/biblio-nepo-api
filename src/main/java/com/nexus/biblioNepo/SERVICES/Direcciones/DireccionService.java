/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Direcciones;

import com.nexus.biblioNepo.DTOS.request.DireccionDtoReq;
import com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionBasicDto;
import com.nexus.biblioNepo.ENTYTIES.direccion;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.REPOSITORIES.ciudadRepository;
import com.nexus.biblioNepo.REPOSITORIES.departamentoRepository;
import com.nexus.biblioNepo.REPOSITORIES.direccionRepository;
import com.nexus.biblioNepo.REPOSITORIES.paisRepository;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class DireccionService implements IDireccionService {

    private direccionRepository direcccionRepo;
    private ciudadRepository ciudadRepo;
    private paisRepository paisRepo;
    private departamentoRepository depRepo;
    private usuarioRepository usuarioRepo;

    @Autowired
    public DireccionService(direccionRepository direcccionRepo, ciudadRepository ciudadRepo, paisRepository paisRepo, departamentoRepository depRepo, usuarioRepository usuarioRepo) {
        this.direcccionRepo = direcccionRepo;
        this.ciudadRepo = ciudadRepo;
        this.paisRepo = paisRepo;
        this.depRepo = depRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @CacheEvict(value = "direcciones", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public direccion create(Integer id_user, DireccionDtoReq direccionDtoReq) {

        direccion dir = new direccion();

        usuarioRepo.findById(id_user)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El usuario no existe en el sistema"));

        llebarDatos(dir, direccionDtoReq);
        AuditableUtils.create(dir, "pruebs", "prueba");

        return direcccionRepo.save(dir);

    }

    @CacheEvict(value = "direcciones", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public direccion updateById(Integer id, DireccionDtoReq direccionDtoReq) {

        direccion dir = direcccionRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("La dirección no existe en el sistema"));

        llebarDatos(dir, direccionDtoReq);

        AuditableUtils.update(dir, "prueba", "prueba");

        return direcccionRepo.save(dir);

    }

    public void llebarDatos(direccion dir, DireccionDtoReq direccionDtoReq) {

        if (direccionDtoReq.getBarrio() != null) {
            dir.setBarrio(direccionDtoReq.getBarrio().trim());

        }

        if (direccionDtoReq.getComplemento() != null) {
            dir.setComplemento(direccionDtoReq.getComplemento().trim());

        }

        if (direccionDtoReq.getMasDetalles() != null) {
            dir.setMasDetalles(direccionDtoReq.getMasDetalles());

        }

        dir.setPais(paisRepo.findById(direccionDtoReq.getId_pais())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El pais no existe en el sistema")));

        dir.setDepartamento(depRepo.findById(direccionDtoReq.getId_departamento())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El departamento no existe en el sistema")));

        dir.setCiudad(ciudadRepo.findById(direccionDtoReq.getId_ciudad())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("La ciduad no existe en el sistema")));

    }

    @Cacheable(value = "direccion-user", key = "#id")
    @Transactional(readOnly = true)
    @Override
    public DireccionBasicDto getByIdUser(Integer id) {

        return direcccionRepo.getByIdUser(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El usuario no tiene direccion en el sistema"));

    }

}
