/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.ENTYTIES.libro_genero;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author luis
 */
@Repository
public interface libro_genero_repository extends JpaRepository<libro_genero, Integer>{
    
    @Query(
    """
    SELECT lgti
    
    FROM libro_genero lgti
    LEFT JOIN lgti.boock lb
    LEFT JOIN lgti.generoLibro gl
    
    WHERE(lgti.isDelete = false)
    AND (lb.id = :id_libro)
    AND (gl.id = :id_gen)
    
    """
    )
    public Optional<libro_genero>LibroYaTieneEseGenero(Long id_libro, Integer id_gen);
}
