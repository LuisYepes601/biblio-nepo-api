/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Libros;

import com.nexus.biblioNepo.DTOS.request.Libro.LibroDtoReq;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.REPOSITORIES.BoockRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
@Service
public class LibroServiceAdmin implements IlibroServiceAdmin {

    private BoockRepository boockRepository;

    @Override
    public Boock create(LibroDtoReq libroDtoReq, MultipartFile portada, MultipartFile libro, MultipartFile fotoAutor) {

        Optional<Boock> existelibro = boockRepository.existeLibroByNombre(libroDtoReq.getTitulo().trim());

        if (existelibro.isPresent()) {

            throw new DatoYaExistenteException("El libro ya existe en el sistema");
        }

        Boock boock = new Boock();

        boock.setTitulo(libroDtoReq.getTitulo().trim());

        if (libroDtoReq.getDescripcion() != null) {
            boock.setDescripcion(libroDtoReq.getDescripcion().trim());

        }
        
        

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
