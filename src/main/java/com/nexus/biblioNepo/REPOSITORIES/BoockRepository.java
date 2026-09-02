/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Libro.DetalleLibroBasicRespDto;
import com.nexus.biblioNepo.DTOS.response.Libro.LibroAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Libro.LibroBasicDtoResonse;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Libro.LibroAdminDtoResp(
           lb.id,
           lb.titulo,
           lb.subtitulo,
           lb.isbn,
           lb.editorial,
           lb.fechaPublicacion,
           lb.edicion,
           lb.portadaUrl,
           lb.publicIdPortada,
           lb.descripcion,
           lb.paisPublicacion,
           for.nombre,
           for.id,
           au.id,
           au.nombre
           
           )
           
           FROM Boock lb
           LEFT JOIN lb.formatoLibro for
           LEFT JOIN lb.autor au
           LEFT JOIN lb.categorias lcti
           LEFT JOIN lcti.categoryBoock cl
           LEFT JOIN lb.libros_generos lgti
           LEFT JOIN lgti.generoLibro gl
           LEFT JOIN lb.idiom idiom
           LEFT JOIN lb.tipoLibro tl
           LEFT JOIN lb.palabras_claves pcti
           
           WHERE(:nombre IS NULL OR LOWER(lb.titulo) LIKE CONCAT(LOWER(CAST(:nombre AS string)),'%'))
           AND (:id_lib IS NULL OR lb.id = :id_lib)
           AND(:isDelete IS NULL OR lb.isDelete = :isDelete )
           AND(:excluyed_by_id IS NULL OR lb.id <> :excluyed_by_id )
           AND(:nombre_autor IS NULL OR LOWER(au.nombre) LIKE CONCAT(LOWER(CAST(:nombre_autor AS string)),'%'))
           AND(:id_autor IS NULL OR au.id = :id_autor)
           AND(:id_cat IS NULL OR cl.id = :id_cat)
           AND (:id_genero IS NULL OR gl.id = :id_genero)
           AND(:id_idiom IS NULL OR idiom.id = :id_idiom)
           AND (:id_tipo_libro IS NULL OR tl.id = :id_tipo_libro)
           
           
           
           """)
    public Page<LibroAdminDtoResp> getAllAdmin(
            @Param(value = "nombre") String nombre,
            @Param(value = "id_lib") Long id_lib,
            @Param(value = "isDelete") Boolean isDelete,
            @Param(value = "excluyed_by_id") Long excluyed_by_id,
            @Param(value = "nombre_autor") String nombre_autor,
            @Param(value = "id_autor") Integer id_autor,
            @Param(value = "id_cat") Integer id_cat,
            @Param(value = "id_genero") Integer id_genero,
            @Param(value = "id_idiom") Integer id_idiom,
            @Param(value = "id_tipo_libro") Integer id_tipo_libro,
            Pageable pageable);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Libro.LibroBasicDtoResonse(
           lb.id,
           lb.titulo,
           lb.subtitulo,
           lb.isbn,
           lb.editorial,
           lb.fechaPublicacion,
           lb.edicion,
           lb.portadaUrl,
           lb.publicIdPortada,
           lb.descripcion,
           lb.paisPublicacion,
           for.nombre,
           for.id,
           au.id,
           au.nombre
           
           )
           
           FROM Boock lb
           LEFT JOIN lb.formatoLibro for
           LEFT JOIN lb.autor au
           LEFT JOIN lb.categorias lcti
           LEFT JOIN lcti.categoryBoock cl
           LEFT JOIN lb.libros_generos lgti
           LEFT JOIN lgti.generoLibro gl
           LEFT JOIN lb.idiom idiom
           LEFT JOIN lb.tipoLibro tl
           LEFT JOIN lb.palabras_claves pcti
           
           WHERE(:nombre IS NULL OR LOWER(lb.titulo) LIKE CONCAT(LOWER(CAST(:nombre AS string)),'%'))
           AND (:id_lib IS NULL OR lb.id = :id_lib)
           AND(lb.isDelete = true)
           AND(:excluyed_by_id IS NULL OR lb.id <> :excluyed_by_id )
           AND(:nombre_autor IS NULL OR LOWER(au.nombre) LIKE CONCAT(LOWER(CAST(:nombre_autor AS string)),'%'))
           AND(:id_autor IS NULL OR au.id = :id_autor)
           AND(:id_cat IS NULL OR cl.id = :id_cat)
           AND (:id_genero IS NULL OR gl.id = :id_genero)
           AND(:id_idiom IS NULL OR idiom.id = :id_idiom)
           AND (:id_tipo_libro IS NULL OR tl.id = :id_tipo_libro)
           
           
           
           """)
    public Page<LibroBasicDtoResonse> getAll(
            @Param(value = "nombre") String nombre,
            @Param(value = "id_lib") Long id_lib,
            @Param(value = "excluyed_by_id") Long excluyed_by_id,
            @Param(value = "nombre_autor") String nombre_autor,
            @Param(value = "id_autor") Integer id_autor,
            @Param(value = "id_cat") Integer id_cat,
            @Param(value = "id_genero") Integer id_genero,
            @Param(value = "id_idiom") Integer id_idiom,
            @Param(value = "id_tipo_libro") Integer id_tipo_libro,
            Pageable pageable);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Libro.DetalleLibroBasicRespDto(
           
           lb.portadaUrl,
           lb.archivoUrl,
           lb.descripcion,
           lb.paisPublicacion,
           id.nombre,
           for.nombre
           
           )
           
           FROM Boock lb
           LEFT JOIN lb.idiom id
           LEFT JOIN lb.formatoLibro for
           
           WHERE (lb.id = :id_libro)
           
           """)
    public Optional<DetalleLibroBasicRespDto> getDetails(@Param(value = "id_libro") Long id_libro);
}
