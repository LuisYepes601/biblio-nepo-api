/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Categoria;

import com.nexus.biblioNepo.DTOS.response.Categoria.CategoriaDtoresp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.categoryBoockRepository;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
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
public class CategoriaService implements ICategoriaService {

    private categoryBoockRepository catBoockRepo;

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

}
