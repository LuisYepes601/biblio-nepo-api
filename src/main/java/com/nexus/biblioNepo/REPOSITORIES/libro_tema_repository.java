/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Temas.TemasDtoResponse;
import com.nexus.biblioNepo.ENTYTIES.libro_tema;
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
public interface libro_tema_repository extends JpaRepository<libro_tema, Integer> {

    @Query("""
           SELECT lt
           
           FROM libro_tema lt
           LEFT JOIN lt.tema t
           LEFT JOIN lt.boock lb
           
           WHERE(:id_libro = lb.id)
           AND (t.isDelete = false)
           AND (LOWER(:tema) = LOWER(t.nombre))
           """)
    public Optional<libro_tema> existeTema(
            @Param(value = "id_libro") Long id_libro,
            @Param(value = "tema") String tema);

    @Query("""
           SELECT lt 
           
           FROM libro_tema lt
           LEFT JOIN lt.tema te
           LEFT JOIN lt.boock lb
           
           WHERE (LOWER(te.nombre) = LOWER(:tema))
           AND (te.isDelete = false)
           AND (lb.id = :id_libro)
           AND ( te.id <> :id_tema)
           
           """)
    public Optional<libro_tema> ExisteTemaExcept(
            @Param(value = "id_tema") Integer id_tema,
            @Param(value = "id_libro") Long id_libro,
            @Param(value = "tema") String tema);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Temas.TemasDtoResponse(
           te.id,
           te.nombre
          
           )
           
           FROM libro_tema lt
           LEFT JOIN lt.tema te
           LEFT JOIN lt.boock lb
           
           WHERE(:id_libro = lb.id)
           AND(te.isDelete = false)
           
           """)
    public Page<TemasDtoResponse> getAll(
            @Param(value = "id_libro") Long id_libro,
            Pageable pageable
    );

}
