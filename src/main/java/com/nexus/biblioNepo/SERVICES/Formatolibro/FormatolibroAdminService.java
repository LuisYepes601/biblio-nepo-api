/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Formatolibro;

import com.nexus.biblioNepo.DTOS.request.Formatolibro.FormatolibroDtoReq;
import com.nexus.biblioNepo.ENTYTIES.formatoLibro;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.REPOSITORIES.formatoLibroRepository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class FormatolibroAdminService implements IFormatoLibroAdminService {

    private formatoLibroRepository formatoLibroRepo;

    @Autowired
    public FormatolibroAdminService(formatoLibroRepository formatoLibroRepo) {
        this.formatoLibroRepo = formatoLibroRepo;
    }

    @CacheEvict(value = "formato-libros", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public formatoLibro create(FormatolibroDtoReq formatolibroDtoReq) {

        Optional<formatoLibro> existeFormato = formatoLibroRepo.findByNameIgnoreCase(formatolibroDtoReq.getNombre().trim());

        if (existeFormato.isPresent()) {

            throw new DatoYaExistenteException("Ya existe el formato en el sistema y se encuentra activo");
        }

        formatoLibro formato = new formatoLibro();

        formato.setNombre(formatolibroDtoReq.getNombre().trim());

        if (formatolibroDtoReq.getDescripcion() != null) {
            formato.setDescripcion(formatolibroDtoReq.getDescripcion().trim());
        }

        AuditableUtils.create(formato, "prueba", "prueba");

        return formatoLibroRepo.save(formato);

    }

    @Caching(
            evict = {
                @CacheEvict(value = "formato-libros", allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    @Override
    public formatoLibro update(Integer id, FormatolibroDtoReq formatolibroDtoReq) {

        formatoLibro formLibro = formatoLibroRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El formato del libro no existe en el sistema"));

        if (formLibro.isIsDelete()) {

            throw new DatoNoExistenteEcxeption("El formato del libro no existe en el sistema.");
        }

        Optional<formatoLibro> existe = formatoLibroRepo.findByNameIgnoreCase(formatolibroDtoReq.getNombre().trim());

        if (existe.isPresent()) {

            if (existe.get().getId() != formLibro.getId()) {

                throw new DatoYaExistenteException("El formato del libro ya existe en el sitema.");
            }
        }

        formLibro.setNombre(formatolibroDtoReq.getNombre().trim());

        if (formatolibroDtoReq.getDescripcion() != null) {

            formLibro.setDescripcion(formatolibroDtoReq.getDescripcion().trim());
        }

        AuditableUtils.update(formLibro, "prueb", "prueba");

        return formatoLibroRepo.save(formLibro);
    }

    @Caching(
            evict = {
                @CacheEvict(value = "formato-libros", allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    @Override
    public formatoLibro deleteById(Integer id) {

        formatoLibro formLibro = formatoLibroRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El formato del libro no existe en el sistema"));

        if (formLibro.isIsDelete()) {
            throw new DatoNoExistenteEcxeption("El formato del libro no se encuentra en el sistema");
        }

        AuditableUtils.delete(formLibro, "prueba", "prueba");

        return formatoLibroRepo.save(formLibro);

    }

}
