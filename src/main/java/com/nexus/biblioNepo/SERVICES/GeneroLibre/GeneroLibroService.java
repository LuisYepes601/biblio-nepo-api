/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.GeneroLibre;

import com.nexus.biblioNepo.DTOS.request.GeneroLibro.GeneroDtoBasicReq;
import com.nexus.biblioNepo.DTOS.response.GenerLibro.GeneroLibroDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import com.nexus.biblioNepo.ENTYTIES.generoLibro;
import com.nexus.biblioNepo.ENTYTIES.libro_genero;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.BoockRepository;
import com.nexus.biblioNepo.REPOSITORIES.generoLibroRepository;
import com.nexus.biblioNepo.REPOSITORIES.libro_genero_repository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import java.util.List;
import java.util.Optional;
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
public class GeneroLibroService implements IGeneroLibroService {

    private generoLibroRepository genLibroRepository;
    private BoockRepository libroRepo;
    private libro_genero_repository libro_gen_rep;

    @Autowired
    public GeneroLibroService(generoLibroRepository genLibroRepository, BoockRepository libroRepo, libro_genero_repository libro_gen_rep) {
        this.genLibroRepository = genLibroRepository;
        this.libroRepo = libroRepo;
        this.libro_gen_rep = libro_gen_rep;
    }

    @Cacheable(value = "genero-libros")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<GeneroLibroDtoResp> getAll(String nombre, Pageable pageable) {

        Page<GeneroLibroDtoResp> page = genLibroRepository.getAll(nombre, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay generos que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void asignarGenerosToLibro(Long id_libro, List<GeneroDtoBasicReq> generos) {

        Boock Libro = libroRepo.findById(id_libro)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El libro no existe en el sistema"));

        if (Libro.isIsDelete()) {
            throw new DatoNoExistenteEcxeption("el libro no existe en el sistema");
        }

        generos.stream()
                .forEach((gen) -> {

                    generoLibro gl = genLibroRepository.findById(gen.getId())
                            .orElseThrow(()
                                    -> new DatoNoExistenteEcxeption("El genero " + gen.getNombre() + " no existe en el sistema"));

                    Optional<libro_genero> existe = libro_gen_rep.LibroYaTieneEseGenero(id_libro, gen.getId());

                    if (existe.isPresent()) {

                        throw new DatoYaExistenteException("No se pueden repetir generos en el libro");

                    }

                    libro_genero lg = new libro_genero();
                    lg.setBoock(Libro);
                    lg.setGeneroLibro(gl);

                    AuditableUtils.create(lg, "prueba", "prueba");
                    libro_gen_rep.save(lg);
                });

    }

}
