/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Autors.AutorAdminDtoResp;
import com.nexus.biblioNepo.ENTYTIES.Autor;
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
public interface autorRepository extends JpaRepository<Autor, Integer> {

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Autors.AutorAdminDtoResp(
           
           a.id,
           a.nombre,
           a.segundoNombre,
           a.primerApellido,
           a.segundoApellido,
           a.fechaNacimiento,
           a.fechaFallecimiento,
           a.urlFoto,
           a.isFallecido,
           p.id,
           p.nombre,
           a.isDelete
           )
           
           FROM Autor a
           LEFT JOIN a.books b
           LEFT JOIN a.nacionalidad p
           LEFT JOIN b.categorias cbti
           LEFT JOIN cbti.categoryBoock cb
           
           WHERE (:name IS NULL OR a.nombre LIKE CONCAT(LOWER(:name), '%'))
           AND (:id_pais IS NULL OR p.id = :id)
           AND (:is_delete IS NULL OR p.isDelete = :is_delete)
           AND (:name_boock IS NULL OR b.titulo = :name_boock)
           AND (:id_categoria_boock IS NULL OR  cb.id = :id_categoria_boock)
           
           """)
    public Page<AutorAdminDtoResp> getAllAutorsAdmin(
            @Param(value = "name") String name,
            @Param(value = "id_pais") Integer id_pais,
            @Param(value = "is_delete") Boolean id_delete,
            @Param(value = "name_boock") String name_boock,
            @Param(value = "id_categoria_boock") Integer id_categoria_boock,
            Pageable pageable);

}
