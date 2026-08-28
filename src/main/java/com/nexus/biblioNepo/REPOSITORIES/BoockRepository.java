/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.ENTYTIES.Boock;
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
public interface BoockRepository extends JpaRepository<Boock, Long> {

    @Query(
            """
    SELECT lb
    
    FROM Boock lb
    
    WHERE (LOWER(:nombre) = LOWER(lb.titulo))
     AND (lb.isDelete = false)
    
    """
    )
    public Optional<Boock> existeLibroByNombre(@Param(value = "nombre") String nombre);
}
