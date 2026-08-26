/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Pais;

import com.nexus.biblioNepo.DTOS.request.Pais.PaisDtoReq;
import com.nexus.biblioNepo.ENTYTIES.Pais;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.REPOSITORIES.paisRepository;
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
public class PaisServiceAdmin implements IPaisServiceAdmin {

    private paisRepository paisRepo;

    @Autowired
    public PaisServiceAdmin(paisRepository paisRepo) {
        this.paisRepo = paisRepo;
    }

    @CacheEvict(value = "paises", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Pais create(PaisDtoReq paisDtoReq) {

        Pais pais = new Pais();

        Optional<Pais> optional = paisRepo.findByNombreIgnoreCase(paisDtoReq.getNombre().trim());

        Optional<Pais> existeIso = paisRepo.findByISO3(paisDtoReq.getIso_3().trim());

        if (existeIso.isPresent()) {
            throw new DatoYaExistenteException("Ya existe un pais que tien ese ISO-3");
        }

        if (optional.isPresent()) {

            new DatoYaExistenteException("El pais ya se encuentra activo en el sistema");
        }

        pais.setNombre(paisDtoReq.getNombre().trim());
        pais.setIso_3(paisDtoReq.getIso_3().trim().toUpperCase());

        AuditableUtils.create(pais, "prueb", "prueba");

        return paisRepo.save(pais);

    }

}
