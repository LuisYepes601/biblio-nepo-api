/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Idioms.IdiomRespDto;
import com.nexus.biblioNepo.ENTYTIES.Idiom;
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
public interface idiomRepository extends JpaRepository<Idiom, Integer> {

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Idioms.IdiomRespDto(
           
           id.id,
           id.nombre
           
           )
           
           FROM Idiom id
           
           WHERE (:nombre IS NULL OR LOWER(id.nombre) LIKE CONCAT(LOWER(CAST(:nombre AS string)), '%'))
           AND (id.isDelete = false)
           """)
    public Page<IdiomRespDto> getAll(
            @Param(value = "nombre") String nombre, Pageable pageable);
}
