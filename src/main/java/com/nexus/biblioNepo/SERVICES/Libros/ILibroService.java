/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Libros;

import com.nexus.biblioNepo.DTOS.response.Libro.DetalleLibroBasicRespDto;
import com.nexus.biblioNepo.DTOS.response.Libro.LibroBasicDtoResonse;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface ILibroService {
    
    public PageResponse<LibroBasicDtoResonse>getAll(
      Long id_lib,
            Long excluyed_by_id,
            String nombre_autor,
            Integer id_autor,
            Integer id_cat,
            Integer id_genero,
            Integer id_idiom,
            Integer id_tipo_libro,
            Pageable pageable);
    
    public DetalleLibroBasicRespDto getDetailsByID(Long id);
}
