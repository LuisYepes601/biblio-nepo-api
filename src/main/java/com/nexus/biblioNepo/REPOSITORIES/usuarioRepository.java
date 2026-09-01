/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.ENTYTIES.usuario;
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
public interface usuarioRepository extends JpaRepository<usuario, Integer> {

    @Query("""
           SELECT u
           
           FROM usuario u
           
           WHERE (LOWER(u.email) = LOWER(:email))
         
           
           """)
    public Optional<usuario> findByEmail(@Param(value = "email") String email);
}
