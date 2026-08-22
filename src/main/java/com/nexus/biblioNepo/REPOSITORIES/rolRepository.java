/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Roles.RolDetailsAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDtoAdminResp;
import com.nexus.biblioNepo.DTOS.response.Roles.RolDtoBasic;
import com.nexus.biblioNepo.ENTYTIES.rol;
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
public interface rolRepository extends JpaRepository<rol, Integer> {

    public Optional<rol> findByNombreIgnoreCase(String nombre);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Roles.RolDtoAdminResp(
           
           r.id, 
           r.nombre,
           r.descripcion,
           r.createAt,
           r.updateAt
           )
           
           FROM rol r
           
           WHERE (:name IS NULL OR LOWER(r.nombre) LIKE CONCAT(LOWER(CAST(:name AS string)), '%'))
           AND (:is_delete IS NULL OR r.isDelete = :is_delete)
           """)
    public Page<RolDtoAdminResp> getAllAdmin(
            @Param(value = "name") String name,
            @Param(value = "is_delete") Boolean is_delete,
            Pageable pageable);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Roles.RolDetailsAdminDtoResp(
           
           r.deleteAt,
           r.isDelete,
           r.createBy,
           r.creatorName,
           r.updateBy,
           r.updateName,
           r.deleteBy,
           r.deleteName
           )
           
           FROM rol r
           WHERE (r.id = :id)
           """)
    public Optional<RolDetailsAdminDtoResp> getDetailsbyId(@Param(value = "id") Integer id);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Roles.RolDtoBasic (
           r.id, 
           r.nombre  
           )
           
           FROM rol r
           
           WHERE r.isDelete = false
           
           """)
    public Page<RolDtoBasic> getAll();
}
