/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Prestamos;

import com.nexus.biblioNepo.DTOS.request.Prestamos.PrestamoDtoreq;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import com.nexus.biblioNepo.ENTYTIES.prestamo;
import com.nexus.biblioNepo.ENTYTIES.usuario;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.REPOSITORIES.BoockRepository;
import com.nexus.biblioNepo.REPOSITORIES.prestamo_repository;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import java.time.LocalDate;
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
public class PrestamosService implements IPrestamosService {

    private BoockRepository bkRepo;

    private usuarioRepository usRepo;

    private prestamo_repository prestRepo;

    @Autowired
    public PrestamosService(BoockRepository bkRepo, usuarioRepository usRepo, prestamo_repository prestRepo) {
        this.bkRepo = bkRepo;
        this.usRepo = usRepo;
        this.prestRepo = prestRepo;
    }

    @CacheEvict(value = "prestamos", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void prestarLibro(PrestamoDtoreq prestamoDtoreq) {

        Boock boock = bkRepo.findById(prestamoDtoreq.getId_libro())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El libro no existe en el sistema"));

        usuario usu = usRepo.findById(prestamoDtoreq.getId_user())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El usuario no existe en el sistema"));

        Optional<prestamo> existePrestamo = prestRepo.existeAndEstaActivoElPrestamo(
                prestamoDtoreq.getId_user(),
                prestamoDtoreq.getId_libro());

        if (existePrestamo.isPresent()) {
            throw new DatoYaExistenteException("Ya tienes prestado ese libro.");
        }

        prestamo pt = new prestamo();

        if (prestamoDtoreq.getFechaPrestamo().isBefore(LocalDate.now())) {

            throw new RuntimeException("La fecha de prestamo no pude ser menor a la actual");
        }

        pt.setFechaPrestamo(prestamoDtoreq.getFechaPrestamo());

        if (prestamoDtoreq.getFecha_venciimiento().isBefore(prestamoDtoreq.getFechaPrestamo())) {

            throw new RuntimeException("La fecha de prestamo no pude ser mayor a la de vencimiento");

        }

        pt.setFecha_venciimiento(prestamoDtoreq.getFecha_venciimiento());

        pt.setHora_prestamo(prestamoDtoreq.getHora_prestamo());
        pt.setHora_vencimiento(prestamoDtoreq.getHora_vencimiento());

        pt.setBoock(boock);
        pt.setUsuario(usu);

        AuditableUtils.create(pt, "prueba", "prueba");

    }

}
