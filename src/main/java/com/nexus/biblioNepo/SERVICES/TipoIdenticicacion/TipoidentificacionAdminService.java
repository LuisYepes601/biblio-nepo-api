/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.TipoIdenticicacion;

import com.nexus.biblioNepo.DTOS.request.TipoIdentificacionReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.TipoIdentificacionAdminResp.TipoIdentificacionDtoAdminResp;
import com.nexus.biblioNepo.DTOS.response.TipoIdentificacionAdminResp.TipoidentificacionDetailsAdminDto;
import com.nexus.biblioNepo.ENTYTIES.tipoidentificacion;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.tipoIdentificacionRepository;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author luis
 */
@Service
public class TipoidentificacionAdminService implements ITipoIdentificacionAdminService {

    private tipoIdentificacionRepository tipoIdentificacionRepo;

    @Autowired
    public TipoidentificacionAdminService(tipoIdentificacionRepository tipoIdentificacionRepo) {
        this.tipoIdentificacionRepo = tipoIdentificacionRepo;
    }

    @CacheEvict(value = "tipo-identificaciones", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public tipoidentificacion create(TipoIdentificacionReq tipoIdentificacionReq) {

        tipoidentificacion ti;

        Optional<tipoidentificacion> optional = tipoIdentificacionRepo.findByNombreIgnoreCase(tipoIdentificacionReq.getNombre().trim());

        if (optional.isPresent()) {

            if (optional.get().isIsDelete() == false) {

                throw new DatoYaExistenteException("El tipo de identificación ya existe en el sistema");
            }

        }

        ti = new tipoidentificacion();

        ti.setNombre(tipoIdentificacionReq.getNombre().toLowerCase().trim());

        if (tipoIdentificacionReq.getDescripcion() != null) {

            ti.setDescripcion(tipoIdentificacionReq.getDescripcion().trim());
        }

        AuditableUtils.create(ti, "prueba", "prueba");

        return tipoIdentificacionRepo.save(ti);
    }

    @Caching(
            evict = {
                @CacheEvict(value = "tipo-identificaciones", allEntries = true),
                @CacheEvict(value = "tipo-identificacion-detail", allEntries = true)
            })
    @Transactional(rollbackFor = Exception.class)
    @Override
    public tipoidentificacion deleteByID(Integer id) {

        tipoidentificacion ti = tipoIdentificacionRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El tipo de identificación no existe en el sistema"));

        if (ti.isIsDelete()) {

            throw new DatoNoExistenteEcxeption("El tipo de identificacion no se encuentra activo en el sistema");
        }

        AuditableUtils.delete(ti, "prueba", "prueba");

        return tipoIdentificacionRepo.save(ti);
    }

    @Caching(
            evict = {
                @CacheEvict(value = "tipo-identificaciones", allEntries = true),
                @CacheEvict(value = "tipo-identificacion-detail", allEntries = true)

            })
    @Transactional(rollbackFor = Exception.class)
    @Override
    public tipoidentificacion updateByid(Integer id, TipoIdentificacionReq tipoIdentificacionReq) {

        tipoidentificacion ti = tipoIdentificacionRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El tipo de identificación no existe en el sistema"));

        if (ti.isIsDelete()) {

            throw new DatoNoExistenteEcxeption("El tipo de identificación no existe en el sistema");

        }

        Optional<tipoidentificacion> optional
                = tipoIdentificacionRepo.findByNombreIgnoreCase(tipoIdentificacionReq.getNombre().trim());

        if (optional.isPresent()) {

            if (optional.get().getId() != ti.getId()) {

                throw new DatoYaExistenteException("El tipo de identificación ya existe en el sistema y se encuentra activo.");

            }
        }

        ti.setNombre(tipoIdentificacionReq.getNombre().toUpperCase().trim());

        if (ti.getDescripcion() != null) {

            ti.setDescripcion(tipoIdentificacionReq.getDescripcion().trim());

        }

        return tipoIdentificacionRepo.save(ti);
    }

    @Cacheable(value = "tipo-identificaciones")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<TipoIdentificacionDtoAdminResp> getAllAdmin(String nombre, Boolean isDelete, Pageable pageable) {

        Page<TipoIdentificacionDtoAdminResp> page = tipoIdentificacionRepo.getAllAdmin(nombre, isDelete, pageable);

        if (page.isEmpty()) {

            throw new NoDatosQueMostrarExecption("No hay tipos de identificación que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);
    }

    @Cacheable(value = "tipo-identificacion-detail", key = "#id")
    @Transactional(readOnly = true)
    @Override
    public TipoidentificacionDetailsAdminDto getDetailsById(Integer id) {

        return tipoIdentificacionRepo.getDetailsById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El tipo de identificación no existe en el sistema"));

    }

}
