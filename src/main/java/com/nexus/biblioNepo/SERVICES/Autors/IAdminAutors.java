/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES;

import com.nexus.biblioNepo.DTOS.request.autorDtoReq;
import com.nexus.biblioNepo.ENTYTIES.Autor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
public interface IAdminAutors {
    
    public Autor create(MultipartFile imgAutor, autorDtoReq dtoReq);
    
    public Autor updateAutorByID(Integer id, MultipartFile imgAutor, autorDtoReq dtoReq);
    
    public Autor deleteById(Integer id);
    
    
}
