/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Libros;

import com.nexus.biblioNepo.DTOS.request.Categoria.CategoriaAsignarDto;
import com.nexus.biblioNepo.DTOS.request.GeneroLibro.GeneroDtoBasicReq;
import com.nexus.biblioNepo.DTOS.request.Libro.LibroDtoReq;
import com.nexus.biblioNepo.DTOS.request.autorDtoReq;
import com.nexus.biblioNepo.DTOS.response.Libro.LibroAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
public interface IlibroServiceAdmin {

    public Boock create(
            LibroDtoReq libroDtoReq,
            MultipartFile portada,
            MultipartFile libro,
            MultipartFile fotoAutor,
            List<CategoriaAsignarDto> categorias,
            Integer id_autor,
            autorDtoReq autorDtoReq,
            List<GeneroDtoBasicReq> generos);

    public PageResponse<LibroAdminDtoResp> getAll(String nombre,
            Long id_lib,
            Boolean isDelete,
            Long excluyed_by_id,
            String nombre_autor,
            Integer id_autor,
            Integer id_cat,
            Integer id_genero,
            Integer id_idiom,
            Integer id_tipo_libro,
            Pageable pageable);
}
