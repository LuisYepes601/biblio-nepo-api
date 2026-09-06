/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Categoria.CategoriaDtoresp;
import com.nexus.biblioNepo.ENTYTIES.libro_categoria;
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
public interface libro_categoria_repository extends JpaRepository<libro_categoria, Integer> {

    @Query("""
           SELECT lcti
           
           FROM libro_categoria lcti
           LEFT JOIN lcti.boock lb
           LEFT JOIN lcti.categoryBoock cb
           
           WHERE (lb.id = :id_libro)
           AND (cb.id = :id_cat)
           AND (lcti.isDelete = false)
           
           """)
    public Optional<libro_categoria> libroTieneCategoria(
            @Param(value = "id_cat") Integer id_cat,
            @Param(value = "id_libro") Long id_libro);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Categoria.CategoriaDtoresp(
           cb.id, 
           cb.nombre
           )
           
           FROM libro_categoria lcti
           LEFT JOIN lcti.boock lb
           LEFT JOIN lcti.categoryBoock cb
           
           WHERE lcti.isDelete = false
           """)
    public Page<CategoriaDtoresp> getByIdLibro(@Param(value = "id_libro") Long id_libro, Pageable pageable);
}
