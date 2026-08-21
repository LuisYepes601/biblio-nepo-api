/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Autors;

import com.nexus.biblioNepo.DTOS.request.autorDtoReq;
import com.nexus.biblioNepo.DTOS.response.Autors.AutorAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Autors.AutorDetailsAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.Cloudinary.CloudinaryUploadResponse;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.ENTYTIES.Autor;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.deleteFileCloudinary;
import com.nexus.biblioNepo.REPOSITORIES.autorRepository;
import com.nexus.biblioNepo.REPOSITORIES.paisRepository;
import com.nexus.biblioNepo.SERVICES.IAdminAutors;
import com.nexus.biblioNepo.SERVICES.cloudinary.ICloudinaryService;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
@Service
public class AdminAutorsService implements IAdminAutors {

    private ICloudinaryService cloudinaryService;
    private autorRepository autorRepo;
    private paisRepository paisRepo;

    @Autowired
    public AdminAutorsService(ICloudinaryService cloudinaryService, autorRepository autorRepo, paisRepository paisRepo) {
        this.cloudinaryService = cloudinaryService;
        this.autorRepo = autorRepo;
        this.paisRepo = paisRepo;
    }

    @CacheEvict(value = "autors", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Autor create(MultipartFile imgAutor, autorDtoReq dtoReq) {

        Autor autor;

        autor = new Autor();

        rellenarDatosAutores(autor, dtoReq);

        AuditableUtils.create(autor, "prueba", "prueba");

        cargarFotoAutor(imgAutor, autor, dtoReq);

        return autorRepo.save(autor);

    }

    @CacheEvict(value = "autors", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Autor updateAutorByID(Integer id, MultipartFile imgAutor, autorDtoReq dtoReq) {

        Autor autor = autorRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El autor no existe en el sistema"));

        if (autor.isIsDelete()) {
            throw new DatoNoExistenteEcxeption("El autor no se encuentra actualmente en el sistema");
        }

        rellenarDatosAutores(autor, dtoReq);

        AuditableUtils.update(autor, "prueba", "prueba");

        actualizarImagenAutor(imgAutor, autor, dtoReq);

        return autorRepo.save(autor);
    }

    public void rellenarDatosAutores(Autor autor, autorDtoReq dtoReq) {

        autor.setNombre(dtoReq.getNombre().trim());
        autor.setFechaNacimiento(dtoReq.getFechaNacimiento());

        if (dtoReq.getIsFallecido() != null) {

            if (dtoReq.getIsFallecido()
                    && dtoReq.getFechaFallecimiento() != null) {

                autor.setIsFallecido(dtoReq.getIsFallecido());
                autor.setFechaFallecimiento(dtoReq.getFechaFallecimiento());
            }
        }
        autor.setNacionalidad(paisRepo.findById(dtoReq.getIdPais())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El pais no existe en el sistema.")));

        autor.setPrimerApellido(dtoReq.getPrimerApellido().trim());
        autor.setSegundoApellido(dtoReq.getSegundoApellido().trim());

        if (dtoReq.getSegundoNombre() != null) {
            autor.setSegundoNombre(dtoReq.getSegundoNombre().trim());

        }
    }

    public void cargarFotoAutor(MultipartFile imgAutor, Autor autor, autorDtoReq dtoReq) {
        if (imgAutor != null) {

            CloudinaryUploadResponse response = cloudinaryService.uploadPrymaryPhotoBoock(
                    imgAutor,
                    dtoReq.getNombre().trim(),
                    imgAutor.getOriginalFilename());

            autor.setUrlFoto(response.getSecureUrl());
            autor.setPublicIdUrlFoto(response.getPublicId());
        }
    }

    public void actualizarImagenAutor(MultipartFile imgAutor, Autor autor, autorDtoReq dtoReq) {

        if (autor.getUrlFoto() == null) {

            cargarFotoAutor(imgAutor, autor, dtoReq);

        }

        cloudinaryService.deleteFile(autor.getPublicIdUrlFoto());
        cargarFotoAutor(imgAutor, autor, dtoReq);

    }

    @Caching(
            evict = {
                @CacheEvict(value = "autors", allEntries = true)
            }
    )
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Autor deleteById(Integer id) {

        Autor autor = autorRepo.findById(id)
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El auator no existe en el sistema"));

        if (autor.isIsDelete()) {

            throw new DatoNoExistenteEcxeption("El autor no se encuentra activo ene el sistema");
        }

        AuditableUtils.delete(autor, "prueba", "prueba");

        return autorRepo.save(autor);

    }

    @Cacheable(value = "autors")
    @Transactional(readOnly = true)
    @Override
    public PageResponse<AutorAdminDtoResp> getAll(String name, Integer id_pais, Boolean id_delete, String name_boock, Integer id_categoria_boock, Pageable pageable) {

        Page<AutorAdminDtoResp> page = autorRepo.getAllAutorsAdmin(
                name, id_pais, id_delete, name_boock,
                id_categoria_boock, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay autores que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);

    }

    @Cacheable(value = "autor-detail-admin", key = "#id")
    @Transactional(readOnly = true)
    @Override
    public AutorDetailsAdminDtoResp getDetailAdmin(Integer id) {

        return autorRepo.getDetailsById(id)
                .orElseThrow(() 
                        -> new DatoNoExistenteEcxeption("El autor no tiene detalles o no existe en el sistema"));

    }

}
