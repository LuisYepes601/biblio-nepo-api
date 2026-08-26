/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Ciudad;

import com.nexus.biblioNepo.ENTYTIES.ciudad;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.REPOSITORIES.ciudadRepository;
import com.nexus.biblioNepo.REPOSITORIES.departamentoRepository;
import com.nexus.biblioNepo.SERVICES.RestClients.ApiColombiaRestclient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class CiudadServiceAdmin implements ICiudadServiceAdmin {

    private ApiColombiaRestclient apiColombiaRestclient;
    private ciudadRepository ciudadRepo;
    private departamentoRepository depRepo;

    @Autowired
    public CiudadServiceAdmin(ApiColombiaRestclient apiColombiaRestclient, ciudadRepository ciudadRepo, departamentoRepository depRepo) {
        this.apiColombiaRestclient = apiColombiaRestclient;
        this.ciudadRepo = ciudadRepo;
        this.depRepo = depRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cargarCiudades() {

        apiColombiaRestclient.getCities()
                .stream()
                .forEach((c) -> {
                    ciudad ciudad = new ciudad();
                    ciudad.setId(c.getId());
                    ciudad.setNombre(c.getName());
                    ciudad.setDepartamento(depRepo.findById(c.getDepartmentId())
                            .orElseThrow(() -> new DatoNoExistenteEcxeption("No existe ese departamento en el sistema")));

                    ciudadRepo.save(ciudad);
                });
    }

}
