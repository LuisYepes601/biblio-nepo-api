/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.ENTYTIES.prestamo;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.Optional;
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
}
