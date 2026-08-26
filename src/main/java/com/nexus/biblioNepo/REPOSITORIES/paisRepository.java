/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Pais.PaisDtoResp;
import com.nexus.biblioNepo.ENTYTIES.Pais;
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
public interface paisRepository extends JpaRepository<Pais, Integer> {

    @Query("""
           SELECT p
           
           FROM Pais p
           
           WHERE (LOWER(p.nombre) = (:nombre))
           AND (p.isDelete = false)
           
           """)
    public Optional<Pais> findByNombreIgnoreCase(@Param(value = "nombre") String nombre);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Pais.PaisDtoResp(
           
           p.id, 
           p.nombre
           )
           
           FROM Pais p
           WHERE p.isDelete = false
           
           """)
    public Page<PaisDtoResp> getAll(Pageable pageable);
    
     @Query("""
           SELECT p
           
           FROM Pais p
           
           WHERE (LOWER(p.iso_3) = (:iso_3))
           AND (p.isDelete = false)
           
           """)
    public Optional<Pais> findByISO3(@Param(value = "iso_3") String iso_3);

}
