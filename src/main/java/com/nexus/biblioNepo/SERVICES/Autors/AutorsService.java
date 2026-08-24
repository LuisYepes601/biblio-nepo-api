/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Autors;

import com.nexus.biblioNepo.DTOS.response.Autors.AutorAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Autors.AutorDetailsBasciDto;
import com.nexus.biblioNepo.DTOS.response.Autors.AutorDtoBasic;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.autorRepository;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class AutorsService implements IAutorService {
    
    private autorRepository autorRepo;
    
    @Autowired
    public AutorsService(autorRepository autorRepo) {
        this.autorRepo = autorRepo;
    }
    
    @Cacheable(value = "autors-basic")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<AutorDtoBasic> getAll(String name, Integer id_pais, String name_boock, Integer id_categoria_boock, Integer excluyed_id, Pageable pageable) {
        
        Page<AutorDtoBasic> page = autorRepo.getAll(name, id_pais, name_boock, id_categoria_boock, excluyed_id, pageable);
        
        if (page.isEmpty()) {
            
            throw new NoDatosQueMostrarExecption("No hay autores que mostrar");
        }
        
        return PageResponseUtils.CreatePageReponse(page);
        
    }
    
    @Cacheable(value = "autor-detail-basic")
    @Transactional(readOnly = true)
    @Override
    public AutorDetailsBasciDto getDetailsBasicByID(Integer id) {
        
        return autorRepo.getDetailsBasic(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El autor no exitse enel sistema"));
    }
    
}
