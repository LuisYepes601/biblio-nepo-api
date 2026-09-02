/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Libros;

import com.nexus.biblioNepo.DTOS.response.Libro.DetalleLibroBasicRespDto;
import com.nexus.biblioNepo.DTOS.response.Libro.LibroBasicDtoResonse;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.BoockRepository;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class LibroService implements ILibroService {
    
    private BoockRepository libroRepo;
    
    ;

    @Autowired
    public LibroService(BoockRepository libroRepo) {
        this.libroRepo = libroRepo;
    }
    
    @Transactional(readOnly = true)
    @Override
    public PageResponse<LibroBasicDtoResonse> getAll(Long id_lib, Long excluyed_by_id, String nombre_autor,
            Integer id_autor, Integer id_cat, Integer id_genero, Integer id_idiom,
            Integer id_tipo_libro, Pageable pageable) {
        
        Page<LibroBasicDtoResonse> page = libroRepo.getAll(nombre_autor, id_lib, excluyed_by_id, nombre_autor,
                id_autor, id_cat, id_genero, id_idiom, id_tipo_libro, pageable);
        
        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay libros que mostrar");
        }
        
        return PageResponseUtils.CreatePageReponse(page);
        
    }
    
    @Override
    public DetalleLibroBasicRespDto getDetailsByID(Long id) {
        
        return libroRepo.getDetails(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El libro no existe en el sistema"));
        
    }
    
}
