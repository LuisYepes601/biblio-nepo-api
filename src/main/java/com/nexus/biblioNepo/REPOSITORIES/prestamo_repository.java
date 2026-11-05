/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Prestamos.PrestamosAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Prestamos.PrestasmosDtoResp;
import com.nexus.biblioNepo.ENTYTIES.prestamo;
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
public interface prestamo_repository extends JpaRepository<prestamo, Integer> {

    @Query("""
           SELECT p
           
           FROM prestamo p
           
           LEFT JOIN p.usuario u
           LEFT JOIN p.boock b
           
           WHERE (u.id = :id_user)
           AND (b.id = :id_libro)
           AND (p.isDelete = false)
           
           """)
    public Optional<prestamo> existeAndEstaActivoElPrestamo(
            @Param(value = "id_user") Long id_user,
            @Param(value = "id_libro") Long id_libro
    );

    @Query("""
    SELECT DISTINCT NEW com.nexus.biblioNepo.DTOS.response.Prestamos.PrestasmosDtoResp(
        p.id,
        p.fechaPrestamo,
        p.fecha_venciimiento,
        p.hora_prestamo,
        p.hora_vencimiento,
        b.id,
        b.titulo,
        b.subtitulo,
        b.isbn,
        b.editorial,
        b.fechaPublicacion,
        b.edicion,
        b.portadaUrl
    )
    FROM prestamo p
    LEFT JOIN p.usuario u
    LEFT JOIN p.boock b
    WHERE u.id = :id_user
    AND p.isDelete = false
    """)
    Page<PrestasmosDtoResp> getPrestamosByIdUser(
            @Param("id_user") Long id_user,
            Pageable pageable
    );

    @Query("""
    SELECT DISTINCT NEW com.nexus.biblioNepo.DTOS.response.Prestamos.PrestamosAdminDtoResp(
        p.id,
        p.fechaPrestamo,
        p.fecha_venciimiento,
        p.hora_prestamo,
        p.hora_vencimiento,
        b.id,
        b.titulo,
        b.subtitulo,
        b.isbn,
        b.editorial,
        b.fechaPublicacion,
        b.edicion,
        b.portadaUrl,
        u.id,
        u.nombre,
        u.segundoNombre,
        u.primerApellido,
        u.segundoApellido,
        u.urlFotoPerfil
    )
           
    FROM prestamo p
    LEFT JOIN p.usuario u
    LEFT JOIN p.boock b
    WHERE (:email IS NULL OR u.email = :email)
    
           
    """)
    Page<PrestamosAdminDtoResp> getPrestamos(
            @Param("email") String email,
            Pageable pageable
    );
}
