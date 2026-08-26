/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Departamentos.DepartamentoBasicDtoResp;
import com.nexus.biblioNepo.ENTYTIES.departamento;
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
public interface departamentoRepository extends JpaRepository<departamento, Integer> {

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Departamentos.DepartamentoBasicDtoResp(
           
           d.id,
           d.nombre
           )
           
           FROM departamento d
           LEFT JOIN d.pais p
           
           WHERE (:nombre IS NULL OR LOWER(d.nombre) LIKE CONCAT(LOWER(CAST(:nombre AS string)), '%') )
           AND (:id_pais IS NULL OR p.id = :id_pais)
           
           
           """)
    public Page<DepartamentoBasicDtoResp> getAllBasic(
            @Param(value = "nombre") String nombre,
            @Param(value = "id_pais") Integer id_pais,
            Pageable pageable);

}
