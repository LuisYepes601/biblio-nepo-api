/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES;

import com.nexus.biblioNepo.DTOS.request.autorDtoReq;
import com.nexus.biblioNepo.DTOS.response.Autors.AutorAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Autors.AutorDetailsAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.ENTYTIES.Autor;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
public interface IAdminAutors {

    public Autor create(MultipartFile imgAutor, autorDtoReq dtoReq);

    public Autor updateAutorByID(Integer id, MultipartFile imgAutor, autorDtoReq dtoReq);

    public Autor deleteById(Integer id);

    public PageResponse<AutorAdminDtoResp> getAll(
            String name,
            Integer id_pais,
            Boolean id_delete,
            String name_boock,
            Integer id_categoria_boock,
            Integer excluyed_id,
            Pageable pageable);
    
    public AutorDetailsAdminDtoResp getDetailAdmin(Integer id);
}
