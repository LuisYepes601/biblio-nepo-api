/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.TipoIdentificacionAdminResp.TipoIdentificacionDtoAdminResp;
import com.nexus.biblioNepo.DTOS.response.TipoIdentificacionAdminResp.TipoidentificacionDetailsAdminDto;
import com.nexus.biblioNepo.ENTYTIES.tipoidentificacion;
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
public interface tipoIdentificacionRepository extends JpaRepository<tipoidentificacion, Integer> {

    @Query("""
           SELECT ti
           
           FROM tipoidentificacion ti
           WHERE ( LOWER(ti.nombre) = LOWER(:nombre))
           AND ti.isDelete = false
           
           """)
    public Optional<tipoidentificacion> findByNombreIgnoreCase(String nombre);

    @Query("""
           
           SELECT NEW  com.nexus.biblioNepo.DTOS.response.TipoIdentificacionAdminResp.TipoIdentificacionDtoAdminResp(
           ti.id,
           ti.nombre,
           ti.descripcion,
           ti.createAt,
           ti.updateAt,
           ti.isDelete
           )
           
           FROM tipoidentificacion ti
           
           WHERE (:nombre IS NULL OR LOWER(ti.nombre) LIKE CONCAT(LOWER(CAST(:nombre AS string)), '%'))
           AND (:is_delete IS NULL OR ti.isDelete = :is_delete)
           """)
    public Page<TipoIdentificacionDtoAdminResp> getAllAdmin(
            @Param(value = "nombre") String nombre,
            @Param(value = "is_delete") Boolean is_delete,
            Pageable pageable);
    
    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.TipoIdentificacionAdminResp.TipoidentificacionDetailsAdminDto(
           
           ti.isDelete,
           ti.createBy,
           ti.creatorName,
           ti.updateBy,
           ti.updateName,
           ti.deleteBy,
           ti.deleteName
           )
           
           FROM tipoidentificacion ti
           
           WHERE (ti.id = :id)
           
           """)
    public Optional<TipoidentificacionDetailsAdminDto>getDetailsById(@Param(value = "id")Integer id);
}
