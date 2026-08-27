/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.TipoLibro;

import com.nexus.biblioNepo.DTOS.request.TipoLibro.TipoLibroDtoReq;
import com.nexus.biblioNepo.ENTYTIES.tipoLibro;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.REPOSITORIES.tipolibroRepository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
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
public class TipoLibroAdminService implements ITipoLibroAdminService {

    private tipolibroRepository tipolibroRepo;

    @Autowired
    public TipoLibroAdminService(tipolibroRepository tipolibroRepo) {
        this.tipolibroRepo = tipolibroRepo;
    }

    @CacheEvict(value = "tipo-libros-basic", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public tipoLibro create(TipoLibroDtoReq tipoLibroDtoReq) {

        Optional<tipoLibro> existe = tipolibroRepo.findByNombreIgnoreCase(tipoLibroDtoReq.getNombre().trim());

        if (existe.isPresent()) {
            throw new DatoYaExistenteException("Ya existe ese tipo de libro en el sistema");

        }

        tipoLibro tLibro = new tipoLibro();

        tLibro.setNombre(tipoLibroDtoReq.getNombre().trim());

        if (tipoLibroDtoReq.getDescripcion() != null) {

            tLibro.setDescripcion(tipoLibroDtoReq.getDescripcion().trim());
        }

        AuditableUtils.create(tLibro, "prueba", "prueba");

        return tipolibroRepo.save(tLibro);

    }

    @CacheEvict(value = "tipo-libros-basic", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public tipoLibro updateById(Integer id, TipoLibroDtoReq tipoLibroDtoReq) {

        tipoLibro tpl = tipolibroRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El tipo de libro no existe en el sistema"));

        if (tpl.isIsDelete()) {

            throw new DatoNoExistenteEcxeption("El tipo de libro no existe en el sistema");

        }

        Optional<tipoLibro> existe = tipolibroRepo.findByNombreIgnoreCase(tipoLibroDtoReq.getNombre().trim());

        if (existe.isPresent()) {

            if (existe.get().getId() != tpl.getId() && existe.get().isIsDelete() == false) {

                throw new DatoYaExistenteException("El tipo de libro ya existe en el sistema");
            }
        }
        tpl.setNombre(tipoLibroDtoReq.getNombre().trim());

        if (tipoLibroDtoReq.getDescripcion() != null) {

            tpl.setDescripcion(tipoLibroDtoReq.getDescripcion().trim());
        }

        AuditableUtils.update(tpl, "prueba", "prueba");

        return tipolibroRepo.save(tpl);

    }

}
