/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.FormatoLibro.FormatoLibrODtoResp;
import com.nexus.biblioNepo.DTOS.response.FormatoLibro.FormatoLibroAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.FormatoLibro.FormatoLibroDetailsAdminDtoResp;
import com.nexus.biblioNepo.ENTYTIES.formatoLibro;
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
public interface formatoLibroRepository extends JpaRepository<formatoLibro, Integer> {

    @Query("""
           SELECT f
           
           FROM formatoLibro f
           
           WHERE (LOWER(f.nombre) = :nombre)
           AND (f.isDelete = false)
           
           """)
    public Optional<formatoLibro> findByNameIgnoreCase(@Param(value = "nombre") String nombre);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.FormatoLibro.FormatoLibrODtoResp(
           f.id,
           f.nombre
           )
           
           FROM formatoLibro f
           
           WHERE (:nombre IS NULL OR LOWER(f.nombre) LIKE CONCAT(LOWER(CAST(:nombre AS string)), '%'))
           AND (f.isDelete = false)
           
           """)
    public Page<FormatoLibrODtoResp> getAll(@Param(value = "nombre") String nombre, Pageable pageable);

    @Query("""
           SELECT DISTINCT NEW com.nexus.biblioNepo.DTOS.response.FormatoLibro.FormatoLibroAdminDtoResp(
           fl.id,
           fl.nombre,
           fl.descripcion
           
           )
           
           FROM formatoLibro fl
           
           WHERE (:nombre IS NULL OR LOWER(fl.nombre) LIKE CONCAT(LOWER(CAST (:nombre AS string)), '%'))
           AND (:is_delete IS NULL OR fl.isDelete = :is_delete)
           
           """)
    public Page<FormatoLibroAdminDtoResp> getAllAdmin(
            @Param(value = "nombre") String nombre,
            @Param(value = "is_delete") Boolean is_delete,
            Pageable pageable);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.FormatoLibro.FormatoLibroDetailsAdminDtoResp(
           
                      fl.deleteAt,
                      fl.isDelete,
                      fl.createBy,
                      fl.creatorName,
                      fl.updateBy,
                      fl.updateName,
                      fl.deleteBy,
                      fl.deleteName
           )
           
           FROM formatoLibro fl
           
           WHERE (fl.id = :id)
           
           """)
    public Optional<FormatoLibroDetailsAdminDtoResp> getDetailsbyId(@Param(value = "id") Long id);
}
