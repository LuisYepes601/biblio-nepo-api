/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Libros;

import com.nexus.biblioNepo.DTOS.response.Libro.DetalleLibroBasicRespDto;
import com.nexus.biblioNepo.DTOS.response.Libro.LibroBasicDtoResonse;
import com.nexus.biblioNepo.DTOS.response.Libro.LibroFavoritoDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import com.nexus.biblioNepo.ENTYTIES.libro_favorito;
import com.nexus.biblioNepo.ENTYTIES.usuario;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.BoockRepository;
import com.nexus.biblioNepo.REPOSITORIES.libro_favorito_repository;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LibroService implements ILibroService {

    private BoockRepository libroRepo;
    private usuarioRepository usuRepository;
    private libro_favorito_repository lfRepo;

    ;

    @Autowired
    public LibroService(BoockRepository libroRepo, usuarioRepository usuRepository, libro_favorito_repository lfRepo) {
        this.libroRepo = libroRepo;
        this.usuRepository = usuRepository;
        this.lfRepo = lfRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<LibroBasicDtoResonse> getAll(Long id_lib, Long excluyed_by_id, String nombre_autor,
            Integer id_autor, Integer id_cat, Integer id_genero, Integer id_idiom,
            Integer id_tipo_libro, Pageable pageable) {

        Page<LibroBasicDtoResonse> page = libroRepo.getAll(nombre_autor, id_lib, excluyed_by_id, nombre_autor,
                id_autor, id_cat, id_genero, id_idiom, id_tipo_libro, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay libros que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

    @Override
    public DetalleLibroBasicRespDto getDetailsByID(Long id) {

        return libroRepo.getDetails(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El libro no existe en el sistema"));

    }

    @Cacheable(value = "libros_favoritos")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void agregarLibroFavorito(Long id_libro, Long id_user) {

        Boock boock = libroRepo.findById(id_libro)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El libro no existe en el sistema"));

        usuario us = usuRepository.findById(id_user)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El usuario no existe en el sistema."));

        Optional<libro_favorito> existe = lfRepo.existeAndEstaActivo(id_libro, id_user);

        if (existe.isPresent()) {
            throw new DatoYaExistenteException("Ya tienes el libro como favorito");
        }

        libro_favorito lf = new libro_favorito();

        lf.setUsuario(us);
        lf.setBoock(boock);

        AuditableUtils.create(lf, "prueba", "prueba");

        lfRepo.save(lf);

    }

    @Cacheable(value = "mis_libros_favoritos", key = "#id_user")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<LibroFavoritoDtoResp> ObtenerMisLibrosFavoritos(Long id_user, Pageable pageable) {

        Page<LibroFavoritoDtoResp> page = lfRepo.ObtenerMisLibrosFavoritos(id_user, pageable);

        if (page.isEmpty()) {

            throw new NoDatosQueMostrarExecption("No tienes libros favoritos");

        }

        return PageResponseUtils.CreatePageReponse(page);

    }

}
