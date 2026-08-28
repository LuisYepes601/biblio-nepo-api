/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.GenerLibro.GeneroLibroDtoResp;
import com.nexus.biblioNepo.ENTYTIES.generoLibro;
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
public interface generoLibroRepository extends JpaRepository<generoLibro, Integer> {

    @Query("""
           SELECT gl
           
           FROM generoLibro gl
           
           WHERE (gl.isDelete = false)
           AND (LOWER(gl.nombre) = LOWER(:nombre))
           
           """)
    public Optional<generoLibro> existeGenerobyNombre(@Param(value = "nombre") String nombre);

    @Query("""
           SELECT gl
           
           FROM generoLibro gl
           
           WHERE (gl.isDelete = false)
           AND (LOWER(gl.nombre) = LOWER(:nombre))
           AND (gl.id <> :id_genero)
           
           """)
    public Optional<generoLibro> existeGenerobyNombreExcepto(
            @Param(value = "nombre") String nombre,
            @Param(value = "id_genero") Integer id_genero);

    @Query(
            """
    SELECT NEW com.nexus.biblioNepo.DTOS.response.GenerLibro.GeneroLibroDtoResp(
    gl.id,
    gl.nombre
    
    )
    FROM generoLibro gl
    
    WHERE(:nombre IS NULL OR LOWER(gl.nombre) LIKE CONCAT(LOWER(CAST(:nombre AS string)) ,'%'))
    AND (gl.isDelete = false)
    """
    )
    public Page<GeneroLibroDtoResp> getAll(@Param(value = "nombre") String nombre, Pageable pageable);
}
