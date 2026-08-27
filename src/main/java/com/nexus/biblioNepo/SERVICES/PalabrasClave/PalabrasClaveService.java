/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.PalabrasClave;

import com.nexus.biblioNepo.DTOS.request.PalabraClaveDtoReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.PalabrasClaves.PalabraClaveDtoResp;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import com.nexus.biblioNepo.ENTYTIES.palabra_clave;
import com.nexus.biblioNepo.ENTYTIES.palabra_clave_libro;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.BoockRepository;
import com.nexus.biblioNepo.REPOSITORIES.palabra_clave_libro_repository;
import com.nexus.biblioNepo.REPOSITORIES.palabra_clave_repository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class PalabrasClaveService implements IPalabrasClave {

    private palabra_clave_repository palClaveRepo;
    private palabra_clave_libro_repository palsClaveRepo;
    private BoockRepository boockRepo;

    @Autowired
    public PalabrasClaveService(palabra_clave_repository palClaveRepo, palabra_clave_libro_repository palsClaveRepo, BoockRepository boockRepo) {
        this.palClaveRepo = palClaveRepo;
        this.palsClaveRepo = palsClaveRepo;
        this.boockRepo = boockRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createByIdLibro(List<PalabraClaveDtoReq> palabras_clave, Long id_libro) {

        Boock boock = boockRepo.findById(id_libro)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El libro no existe en el sistema"));

        palabras_clave.stream()
                .forEach((pc) -> {

                    if (palsClaveRepo.existePalabraClaveEnLibro(id_libro, pc.getNombre().trim())) {

                        throw new DatoYaExistenteException("El libro ya tiene esa palabra clave");
                    }
                    palabra_clave palabra = new palabra_clave();

                    palabra.setNombre(pc.getNombre().trim().toUpperCase());

                    if (pc.getDescripcion() != null) {
                        palabra.setDescripcion(pc.getDescripcion());
                    }

                    AuditableUtils.create(palabra, "prueba", "prueba");

                    palabra_clave_libro pcl = new palabra_clave_libro();

                    pcl.setBoock(boock);
                    pcl.setPalabra_clave(palClaveRepo.save(palabra));

                    palsClaveRepo.save(pcl);

                });

    }

    @Cacheable(value = "palabras-claves-basic")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<PalabraClaveDtoResp> getPalabrasClavesbyIdLibro(Long id_libro, Pageable pageable) {

        Page<PalabraClaveDtoResp> page = palsClaveRepo.getPalabrasClavesByIdLibro(id_libro, pageable);

        if (page.isEmpty()) {

            throw new NoDatosQueMostrarExecption("No hay palabras claves que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);
    }

    @Cacheable(value = "palabras-claves-basic")
    @Transactional(readOnly = true)
    @Override
    public void editarPalabraClave(Integer id_palabra, Long id_libro, PalabraClaveDtoReq palabraClaveDtoReq) {

        palabra_clave pc = palClaveRepo.findById(id_palabra)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("No existe la palabra calve en este libro"));

        if (palsClaveRepo.existePalabraClaveEnLibroExcepto(id_libro, palabraClaveDtoReq.getNombre().trim(), id_palabra)) {

            throw new DatoYaExistenteException("Ya existe la palabra clave en el libro");
        };

        pc.setNombre(palabraClaveDtoReq.getNombre().trim());

        AuditableUtils.update(pc, "prueba", "prueba");

        palClaveRepo.save(pc);
    }

}
