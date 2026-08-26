/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.City.CityDtoResp;
import com.nexus.biblioNepo.ENTYTIES.ciudad;
import io.lettuce.core.dynamic.annotation.Param;
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
public interface ciudadRepository extends JpaRepository<ciudad, Integer> {

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.City.CityDtoResp(
           
           c.id, 
           c.nombre
           )
           FROM ciudad c
           LEFT JOIN c.departamento d
           
           WHERE (:nombre IS NULL OR LOWER(c.nombre) LIKE CONCAT( LOWER(CAST(:nombre AS string)), '%') )
           AND (:id_dep IS NULL OR d.id = :id_dep)
           
           
           """)
    public Page<CityDtoResp> getAll(
            @Param(value = "nombre") String nombre,
            @Param(value = "id_dep") Integer id_dep,
            Pageable pageable);
}
