/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Categoria.CategoriaDtoresp;
import com.nexus.biblioNepo.ENTYTIES.categoryBoock;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author luis
 */
@Repository
public interface categoryBoockRepository extends JpaRepository<categoryBoock, Integer> {

    @Query("""
           
           SELECT cb
           
           FROM categoryBoock cb
           
           WHERE (LOWER(cb.nombre) = :nombre)
           AND (cb.isDelete = false)
           """)
    public Optional<categoryBoock> findByNombreIgnoreCase(@Param(value = "nombre") String nombre);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Categoria.CategoriaDtoresp(
           
           cb.id, 
           cb.nombre
           )
           FROM categoryBoock cb
           
           WHERE(:nombre IS NULL OR LOWER(cb.nombre) LIKE CONCAT(LOWER(CAST(:nombre AS string)), '%'))
           AND (cb.isDelete = false)
           
           """)
    public Page<CategoriaDtoresp> getAll(@Param(value = "nombre") String nombre, Pageable pageable);
}
