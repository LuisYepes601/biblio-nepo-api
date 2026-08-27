/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.TipoLibro.TipoLibroDtoResp;
import com.nexus.biblioNepo.ENTYTIES.tipoLibro;
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
public interface tipolibroRepository extends JpaRepository<tipoLibro, Integer> {

    @Query("""
           SELECT tl
           
           FROM tipoLibro tl
           
           WHERE(LOWER(tl.nombre) = LOWER(:name))
           AND (tl.isDelete = false)
           
           """)
    public Optional<tipoLibro> findByNombreIgnoreCase(@Param(value = "name") String name);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.TipoLibro.TipoLibroDtoResp(
           tl.id, 
           tl.nombre
           )
           
           FROM tipoLibro tl
           
           WHERE (:nombre IS NULL OR LOWER(tl.nombre) LIKE CONCAT(LOWER(CAST(:nombre AS string)),'%'))
           AND(tl.isDelete = false)
           """)
    public Page<TipoLibroDtoResp> getAll(@Param(value = "nombre") String nombre, Pageable pageable);
}
