/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Autors;

import com.nexus.biblioNepo.DTOS.request.autorDtoReq;
import com.nexus.biblioNepo.DTOS.response.Cloudinary.CloudinaryUploadResponse;
import com.nexus.biblioNepo.ENTYTIES.Autor;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.deleteFileCloudinary;
import com.nexus.biblioNepo.REPOSITORIES.autorRepository;
import com.nexus.biblioNepo.REPOSITORIES.paisRepository;
import com.nexus.biblioNepo.SERVICES.IAdminAutors;
import com.nexus.biblioNepo.SERVICES.cloudinary.ICloudinaryService;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
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

}
