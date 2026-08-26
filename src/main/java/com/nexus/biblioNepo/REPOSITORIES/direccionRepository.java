/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionBasicDto;
import com.nexus.biblioNepo.ENTYTIES.direccion;
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
public interface direccionRepository extends JpaRepository<direccion, Integer> {

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionBasicDto(
           
           d.id,
           d.barrio,
           d.complemento,
           d.masDetalles,
           p.id,
           p.nombre,
           dp.id,
           dp.nombre,
           c.id,
           c.nombre
           )
           
           FROM direccion d
           LEFT JOIN d.pais p
           LEFT JOIN d.departamento dp
           LEFT JOIN d.ciudad c
           LEFT JOIN d.usuario us
           
           WHERE us.id = :id
           
           """)
    public Optional<DireccionBasicDto> getByIdUser(@Param("id") Integer id);

    @Query("""
            SELECT NEW com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionAdminDtoResp(
                      
                      us.id,
                      us.nombre,
                      d.createAt,
                      d.updateAt,
                      d.id,
                      d.barrio,
                      d.complemento,
                      d.masDetalles,
                      p.id,
                      p.nombre,
                      dp.id,
                      dp.nombre,
                      c.id,
                      c.nombre
                     
           
                      )
                      
                      FROM direccion d
                      LEFT JOIN d.pais p
                      LEFT JOIN d.departamento dp
                      LEFT JOIN d.ciudad c
                      LEFT JOIN d.usuario us
           
                      WHERE (us.id = :id)
           
           """)
    public Optional<DireccionAdminDtoResp> getDirByIdUserAdmin(@Param(value = "id") Integer id);
}
