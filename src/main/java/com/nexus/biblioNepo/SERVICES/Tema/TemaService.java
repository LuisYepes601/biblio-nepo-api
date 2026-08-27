/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Tema;

import com.nexus.biblioNepo.DTOS.request.Tema.TemaDtoReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.Temas.TemasDtoResponse;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import com.nexus.biblioNepo.ENTYTIES.libro_tema;
import com.nexus.biblioNepo.ENTYTIES.tema;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.BoockRepository;
import com.nexus.biblioNepo.REPOSITORIES.libro_tema_repository;
import com.nexus.biblioNepo.REPOSITORIES.temaRepository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
public class TemaService implements ITemaService {

    private temaRepository temarepo;
    private libro_tema_repository libroTemaRepo;
    private BoockRepository libroRepo;

    @Autowired
    public TemaService(temaRepository temarepo, libro_tema_repository libroTemaRepo, BoockRepository libroRepo) {
        this.temarepo = temarepo;
        this.libroTemaRepo = libroTemaRepo;
        this.libroRepo = libroRepo;
    }

    @CacheEvict(value = "temas-basic", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createtemaLibro(Long id_libro, List<TemaDtoReq> temas) {

        Boock libro = libroRepo.findById(id_libro)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El biro no existe en el sistema"));

        if (libro.isIsDelete()) {

            throw new DatoNoExistenteEcxeption("El libro no se encuentra activo en el sistema");
        }

        List<tema> temasMap = temas.stream()
                .map((t) -> {

                    Optional<libro_tema> existe = libroTemaRepo.existeTema(libro.getId(), t.getNombre().trim());

                    if (existe.isPresent()) {
                        throw new DatoYaExistenteException("El libro ya tiene ese tema activo en el sistema");

                    }

                    tema tema = new tema();

                    tema.setNombre(t.getNombre().trim());

                    if (t.getDescripcion() != null) {

                        tema.setDescripcion(t.getDescripcion().trim());
                    }
                    AuditableUtils.create(tema, "prueb", "prueba");

                    temarepo.save(tema);

                    libro_tema libro_tema = new libro_tema();

                    libro_tema.setBoock(libro);
                    libro_tema.setTema(tema);

                    libroTemaRepo.save(libro_tema);

                    return tema;
                }).toList();

    }

    @CacheEvict(value = "temas-basic", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(Integer id_tema, Long id_libro, TemaDtoReq temaDtoReq) {

        tema tem = temarepo.findById(id_tema)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El tema no existe en el sistema"));

        if (tem.isIsDelete()) {
            throw new DatoNoExistenteEcxeption("El tema no se encuentra en el sistema");
        }

        Optional<libro_tema> existe = libroTemaRepo.ExisteTemaExcept(id_tema, id_libro, temaDtoReq.getNombre().trim());

        if (existe.isPresent()) {

            throw new DatoYaExistenteException("El libro ya tiene ese tema");
        }

        tem.setNombre(temaDtoReq.getNombre().trim());

        if (temaDtoReq.getDescripcion() != null) {

            tem.setDescripcion(temaDtoReq.getDescripcion().trim());
        }

        temarepo.save(tem);
    }

    @Cacheable(value = "temas-basic")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<TemasDtoResponse> getAll(Long id_libro, Pageable pageable) {

        Page<TemasDtoResponse> page = libroTemaRepo.getAll(id_libro, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("El libro no tiene temas");
        }

        return PageResponseUtils.CreatePageReponse(page);
    }

}
