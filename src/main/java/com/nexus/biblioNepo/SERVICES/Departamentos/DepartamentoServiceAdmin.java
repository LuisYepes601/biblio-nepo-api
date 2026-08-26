/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Departamentos;

import com.nexus.biblioNepo.ENTYTIES.departamento;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.REPOSITORIES.departamentoRepository;
import com.nexus.biblioNepo.REPOSITORIES.paisRepository;
import com.nexus.biblioNepo.SERVICES.RestClients.ApiColombiaRestclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class DepartamentoServiceAdmin implements IDepartamentoServiceAdmin {
    
    private ApiColombiaRestclient apiColombiaRestclient;
    private departamentoRepository depRepo;
    private paisRepository paisRepo;
    
    @Autowired
    public DepartamentoServiceAdmin(ApiColombiaRestclient apiColombiaRestclient, departamentoRepository depRepo, paisRepository paisRepo) {
        this.apiColombiaRestclient = apiColombiaRestclient;
        this.depRepo = depRepo;
        this.paisRepo = paisRepo;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cargarDepartamentosByAPI() {
        
        apiColombiaRestclient.getDepartamentos()
                .stream()
                .forEach((dep) -> {
                    departamento de = new departamento();
                    de.setId(dep.getId());
                    de.setNombre(dep.getNombre());
                    if (dep.getCityCapital() != null) {
                        de.setCodePostal(dep.getCityCapital().getPostalCode());
                        
                    }
                    de.setPais(paisRepo.findByNombreIgnoreCase("colombia")
                            .orElseThrow(() -> new DatoNoExistenteEcxeption("El pais no existe en el sistema")));
                    
                    depRepo.save(de);
                    
                });
    }
    
}
