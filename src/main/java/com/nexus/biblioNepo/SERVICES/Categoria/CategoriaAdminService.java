/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Categoria;

import com.nexus.biblioNepo.DTOS.request.Categoria.CategoriaDtoReq;
import com.nexus.biblioNepo.ENTYTIES.categoryBoock;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.REPOSITORIES.categoryBoockRepository;
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
public class CategoriaAdminService implements ICategoriaAdminService {

    private categoryBoockRepository catBoockRepo;

    @Autowired
    public CategoriaAdminService(categoryBoockRepository catBoockRepo) {
        this.catBoockRepo = catBoockRepo;
    }

    @CacheEvict(value = "categorias-basic", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public categoryBoock create(CategoriaDtoReq categoriaDtoReq) {

        Optional<categoryBoock> existe = catBoockRepo.findByNombreIgnoreCase(categoriaDtoReq.getNombre().trim());

        if (existe.isPresent()) {

            throw new DatoNoExistenteEcxeption("La categoria ya existe en el sistema");

        }

        categoryBoock cb = new categoryBoock();

        cb.setNombre(categoriaDtoReq.getNombre().trim());

        if (categoriaDtoReq.getDescripcion() != null) {

            cb.setDescripcion(categoriaDtoReq.getDescripcion().trim());
        }

        AuditableUtils.create(cb, "prueba", "prueba");

        return catBoockRepo.save(cb);
    }

    @CacheEvict(value = "categorias-basic", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public categoryBoock updateById(Integer id, CategoriaDtoReq categoriaDtoReq) {

        categoryBoock cb = catBoockRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("La categoria no existe en el sistema"));

        if (cb.isIsDelete()) {
            throw new DatoNoExistenteEcxeption("La categoria no existe en el sistema");
        }

        Optional<categoryBoock> existe = catBoockRepo.findByNombreIgnoreCase(categoriaDtoReq.getNombre().trim());

        if (existe.isPresent()) {
            throw new DatoYaExistenteException("La categoria ya existe en el sistema");
        }

        cb.setNombre(categoriaDtoReq.getNombre().trim());

        if (categoriaDtoReq.getDescripcion() != null) {

            cb.setDescripcion(categoriaDtoReq.getDescripcion().trim());
        }

        AuditableUtils.create(cb, "prueba", "prueba");

        return catBoockRepo.save(cb);

    }

    @CacheEvict(value = "categorias-basic", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public categoryBoock deleteByID(Integer id) {

        categoryBoock cb = catBoockRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("La categoria no existe en el sistema"));

        if (cb.isIsDelete()) {
            throw new DatoNoExistenteEcxeption("La categoria no existe en el sistema o se encuentra desactivada");
        }

        AuditableUtils.delete(cb, "prueba", "prueba");

        return catBoockRepo.save(cb);
    }

}
