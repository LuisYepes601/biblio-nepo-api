/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.REPOSITORIES;

import com.nexus.biblioNepo.DTOS.response.Users.UserDetailsAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Users.UserDtoAdminResponse;
import com.nexus.biblioNepo.ENTYTIES.departamento;
import com.nexus.biblioNepo.ENTYTIES.usuario;
import io.lettuce.core.dynamic.annotation.Param;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
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
public interface usuarioRepository extends JpaRepository<usuario, Long> {

    @Query("""
           SELECT u
           
           FROM usuario u
           
           WHERE (LOWER(u.email) = LOWER(:email))
         
           
           """)
    public Optional<usuario> findByEmail(@Param(value = "email") String email);

    @Query("""
           SELECT NEW com.nexus.biblioNepo.DTOS.response.Users.UserDtoAdminResponse(
           us.id,
           us.nombre,
           us.segundoNombre,
           us.primerApellido,
           us.segundoApellido,
           us.fechaNacimiento,
           us.urlFotoPerfil,
           us.publicIdUrlFotoPerfil,
           us.email,
           us.numeroIdentificacion
           
           )
           
           FROM usuario us
           LEFT JOIN us.rol rol
           
           WHERE (:num_identificacion IS NULL OR us.numeroIdentificacion LIKE CONCAT(CAST(:num_identificacion AS string),'%'))
           AND (:email IS NULL OR LOWER(us.email) LIKE CONCAT(LOWER(CAST(:email AS string)),'%'))
           AND (:id_rol IS NULL OR rol.id = :id_rol)
           AND(:nombre IS NULL OR LOWER(us.nombre) LIKE CONCAT(LOWER(CAST(:nombre AS string)),'%'))
           AND (:primer_apellido IS NULL OR LOWER(us.primerApellido) LIKE CONCAT(LOWER(CAST(:primer_apellido AS string)),'%'))
    
           
           
           """)
    public Page<UserDtoAdminResponse> getAllAdmin(
            @Param(value = "num_identificacion") String num_identificacion,
            @Param(value = "email") String email,
            @Param(value = "id_rol") Integer id_rol,
            @Param(value = "nombre") String nombre,
            @Param(value = "primer_apellido") String primer_apellido,
            Pageable pageable);

    @Query("""
           SELECT DISTINCT NEW com.nexus.biblioNepo.DTOS.response.Users.UserDetailsAdminDtoResp(
           ti.nombre,
           rol.nombre,
           dir.barrio,
           dir.complemento,
           dir.masDetalles,
           pais.nombre,
           dep.nombre,
           ciu.nombre
           
           )
           
           FROM usuario us
           LEFT JOIN us.tipoIdentificacion ti
           LEFT JOIN us.rol rol
           LEFT JOIN us.direccion dir
           LEFT JOIN dir.pais pais
           LEFT JOIN dir.departamento dep
           LEFT JOIN dir.ciudad ciu
           
           WHERE (us.id = :id)
           
           """)
    public Optional<UserDetailsAdminDtoResp> getDetailsById(@Param(value = "id") Long id);
}
