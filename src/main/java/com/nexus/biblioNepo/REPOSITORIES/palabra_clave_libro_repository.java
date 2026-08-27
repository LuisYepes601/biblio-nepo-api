/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.PalabrasClaves.PalabraClaveDtoResp;
import com.nexus.biblioNepo.ENTYTIES.palabra_clave_libro;
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
public interface palabra_clave_libro_repository extends JpaRepository<palabra_clave_libro, Integer> {

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.PalabrasClaves.PalabraClaveDtoResp(
           pc.id,
           pc.nombre
           )
           
           FROM palabra_clave_libro pcl
           LEFT JOIN pcl.boock li
           LEFT JOIN pcl.palabra_clave pc
           
           WHERE (:id_libro = li.id)
           AND (pc.isDelete = false)
           """)
    public Page<PalabraClaveDtoResp> getPalabrasClavesByIdLibro(@Param(value = "id_libro") Long id_libro, Pageable pageable);

    @Query("""
         SELECT CASE WHEN COUNT(pcl) > 0 THEN true ELSE false END
         FROM palabra_clave_libro pcl
         JOIN pcl.boock li
         JOIN pcl.palabra_clave pc
           
         WHERE li.id = :idLibro
           AND LOWER(pc.nombre) = LOWER(:nombre)
           AND(pc.isDelete = false)
     """)
    boolean existePalabraClaveEnLibro(
            @Param("idLibro") Long idLibro,
            @Param("nombre") String nombre
    );

    @Query("""
        SELECT CASE WHEN COUNT(pcl) > 0 THEN true ELSE false END
           
        FROM palabra_clave_libro pcl
        JOIN pcl.boock li
        JOIN pcl.palabra_clave pc
           
        WHERE li.id = :idLibro
          AND LOWER(pc.nombre) = LOWER(:nombre)
          AND pc.id <> :idPalabra
          AND pc.isDelete = false
    """)
    boolean existePalabraClaveEnLibroExcepto(
            @Param("idLibro") Long idLibro,
            @Param("nombre") String nombre,
            @Param("idPalabra") Integer idPalabra
    );
}
