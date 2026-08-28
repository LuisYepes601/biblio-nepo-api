/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.GeneroLibre;

import com.nexus.biblioNepo.DTOS.request.GeneroLibro.GenerolibroDtoReq;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import com.nexus.biblioNepo.ENTYTIES.generoLibro;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.REPOSITORIES.BoockRepository;
import com.nexus.biblioNepo.REPOSITORIES.generoLibroRepository;
import com.nexus.biblioNepo.REPOSITORIES.libro_genero_repository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class GeneroLibroServiceAdmin implements IGeneroLibroServiceAdmin {

    private generoLibroRepository genLibroRepository;

    @Autowired
    public GeneroLibroServiceAdmin(generoLibroRepository genLibroRepository) {
        this.genLibroRepository = genLibroRepository;
    }

    @CacheEvict(value = "genero-libros-admin", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public generoLibro create(GenerolibroDtoReq generolibroDtoReq) {

        Optional<generoLibro> existelibro = genLibroRepository
                .existeGenerobyNombre(generolibroDtoReq.getNombre().trim());

        if (existelibro.isPresent()) {

            throw new DatoYaExistenteException("El genero de libro ya existe en el sistema");
        }

        generoLibro gLibro = new generoLibro();

        gLibro.setNombre(generolibroDtoReq.getNombre().trim());

        if (generolibroDtoReq.getDescripcion() != null) {

            gLibro.setDescripcion(generolibroDtoReq.getDescripcion().trim());
        }
        AuditableUtils.create(gLibro, "prueba", "prueba");

        return genLibroRepository.save(gLibro);

    }

    @CacheEvict(value = "genero-libros-admin", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public generoLibro updateById(Integer id, GenerolibroDtoReq generolibroDtoReq) {

        generoLibro gLibro = genLibroRepository.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El genero no existe en el sistema"));

        if (gLibro.isIsDelete()) {

            throw new DatoNoExistenteEcxeption("El genero no existe en el sistema");
        }

        Optional<generoLibro> existeOtroConMismoNombre = genLibroRepository.
                existeGenerobyNombreExcepto(generolibroDtoReq.getNombre().trim(), id);

        if (existeOtroConMismoNombre.isPresent()) {

            throw new DatoYaExistenteException("El genero ya existe en el sistema");
        }

        gLibro.setNombre(generolibroDtoReq.getNombre().trim());

        if (generolibroDtoReq.getDescripcion() != null) {

            gLibro.setDescripcion(generolibroDtoReq.getDescripcion());
        }

        AuditableUtils.delete(gLibro, "prueba", "prueba");

        return genLibroRepository.save(gLibro);
    }

    @CacheEvict(value = "genero-libros-admin", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public generoLibro deleteByID(Integer id) {

        generoLibro gLibro = genLibroRepository.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El genero no existe en el sistema"));

        if (gLibro.isIsDelete()) {
            throw new DatoNoExistenteEcxeption("El genero no existe en el sistema");
        }

        AuditableUtils.delete(gLibro, "prueb", "prueba");

        return genLibroRepository.save(gLibro);
    }

}
