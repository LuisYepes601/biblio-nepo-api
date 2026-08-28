/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Categoria;

import com.nexus.biblioNepo.DTOS.request.Categoria.CategoriaAsignarDto;
import com.nexus.biblioNepo.DTOS.response.Categoria.CategoriaDtoresp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import com.nexus.biblioNepo.ENTYTIES.libro_categoria;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.BoockRepository;
import com.nexus.biblioNepo.REPOSITORIES.categoryBoockRepository;
import com.nexus.biblioNepo.REPOSITORIES.libro_categoria_repository;
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
public class CategoriaService implements ICategoriaService {

    private categoryBoockRepository catBoockRepo;
    private libro_categoria_repository libro_cat_repo;
    private BoockRepository libroRepo;

    @Autowired
    public CategoriaService(categoryBoockRepository catBoockRepo) {
        this.catBoockRepo = catBoockRepo;
    }

    @Cacheable(value = "categorias-basic")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<CategoriaDtoresp> getAll(String nombre, Pageable pageable) {

        Page<CategoriaDtoresp> page = catBoockRepo.getAll(nombre, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay categorias que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

    @CacheEvict(value = "categorias-basic", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void asignarCategoriaLibro(Long id_libro, List<CategoriaAsignarDto> categorias) {

        Optional<Boock> existelibro = libroRepo.findById(id_libro);

        if (existelibro.isPresent()) {
            throw new DatoYaExistenteException("El libro ay existe en el sistema");
        }

        if (existelibro.get().isIsDelete()) {

            throw new DatoNoExistenteEcxeption("El libro no existe en el sistema");
        }

        categorias.stream()
                .forEach((c) -> {

                    Optional<libro_categoria> existe = libro_cat_repo.libroTieneCategoria(c.getId(), id_libro);

                    if (existe.isPresent()) {

                        throw new DatoYaExistenteException("El libro ya tiene la categoria: "
                                + existe.get().getCategoryBoock().getNombre());
                    }

                    if (!existe.isPresent()) {

                        libro_categoria lc = new libro_categoria();

                        lc.setBoock(existelibro.get());
                        lc.setCategoryBoock(catBoockRepo.findById(c.getId())
                                .orElseThrow(()
                                        -> new DatoNoExistenteEcxeption("La categoria no exite en el sistema")));

                        AuditableUtils.create(lc, "prueba", "prueba");

                        libro_cat_repo.save(lc);

                    }
                });

    }

}
