/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Libro.LibroFavoritoDtoResp;
import com.nexus.biblioNepo.ENTYTIES.libro_favorito;
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
public interface libro_favorito_repository extends JpaRepository<libro_favorito, Integer> {

    @Query("""
           SELECT lf
           
           FROM libro_favorito lf
           LEFT JOIN lf.boock lib
           LEFT JOIN lf.usuario us
           
           WHERE (lib.id = :id_libro)
           AND (us.id = :id_user)
           AND (lf.isDelete = false)
           
           """)
    public Optional<libro_favorito> existeAndEstaActivo(
            @Param(value = "id_libro") Long id_libro,
            @Param(value = "id_user") Long id_user);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Libro.LibroFavoritoDtoResp(
           lib.id,
           lib.titulo,
           lib.subtitulo,
           lib.isbn,
           lib.editorial,
           lib.fechaPublicacion,
           lib.edicion,
           lib.portadaUrl,
           lib.publicIdPortada,
           lib.descripcion,
           lib.paisPublicacion,
           fl.nombre,
           fl.id,
           au.id,
           au.nombre
           
           )
           
           FROM libro_favorito lf
           LEFT JOIN lf.boock lib
           LEFT JOIN lib.formatoLibro fl
           LEFT JOIN lib.autor au
           LEFT JOIN lf.usuario us
           
           WHERE us.id = :id_user
           
           """)
    public Page<LibroFavoritoDtoResp> ObtenerMisLibrosFavoritos(
            @Param(value = "id_user") Long id_user,
            Pageable pageable);
}
