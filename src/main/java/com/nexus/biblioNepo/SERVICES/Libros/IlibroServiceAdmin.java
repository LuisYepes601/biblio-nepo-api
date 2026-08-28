/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Libros;

import com.nexus.biblioNepo.DTOS.request.Libro.LibroDtoReq;
import com.nexus.biblioNepo.ENTYTIES.Boock;
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
            MultipartFile fotoAutor);
}
