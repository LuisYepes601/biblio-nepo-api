/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Direcciones;

import com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Direcciones.DireccionBasicDto;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.REPOSITORIES.direccionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class DireccionServiceAdmin implements IDireccionServiceADMIN {

    private direccionRepository dirRepo;

    @Autowired
    public DireccionServiceAdmin(direccionRepository dirRepo) {
        this.dirRepo = dirRepo;
    }

    @Cacheable(value = "direccion-user-admin")
    @Transactional(readOnly = true)
    @Override
    public DireccionAdminDtoResp getByIdUser(Integer id) {

        return dirRepo.getDirByIdUserAdmin(id)
                .orElseThrow(()
                        -> new DatoNoExistenteEcxeption("El usuario no existe en el sistema o no tiene dirección"));

    }

}
