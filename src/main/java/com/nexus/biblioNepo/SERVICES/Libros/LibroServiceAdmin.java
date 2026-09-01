/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Libros;

import com.nexus.biblioNepo.DTOS.request.Categoria.CategoriaAsignarDto;
import com.nexus.biblioNepo.DTOS.request.GeneroLibro.GeneroDtoBasicReq;
import com.nexus.biblioNepo.DTOS.request.Libro.LibroDtoReq;
import com.nexus.biblioNepo.DTOS.request.autorDtoReq;
import com.nexus.biblioNepo.DTOS.response.Cloudinary.CloudinaryUploadResponse;
import com.nexus.biblioNepo.DTOS.response.Libro.LibroAdminDtoResp;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.ENTYTIES.Autor;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoYaExistenteException;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import com.nexus.biblioNepo.REPOSITORIES.BoockRepository;
import com.nexus.biblioNepo.REPOSITORIES.autorRepository;
import com.nexus.biblioNepo.REPOSITORIES.formatoLibroRepository;
import com.nexus.biblioNepo.REPOSITORIES.idiomRepository;
import com.nexus.biblioNepo.REPOSITORIES.tipolibroRepository;
import com.nexus.biblioNepo.SERVICES.Autors.IAutorService;
import com.nexus.biblioNepo.SERVICES.Categoria.CategoriaService;
import com.nexus.biblioNepo.SERVICES.Email.EmailService;
import com.nexus.biblioNepo.SERVICES.GeneroLibre.IGeneroLibroService;
import com.nexus.biblioNepo.SERVICES.IAdminAutors;
import com.nexus.biblioNepo.SERVICES.cloudinary.cloudinaryServices;
import com.nexus.biblioNepo.UTILS.AuditableUtils;
import com.nexus.biblioNepo.UTILS.PageResponseUtils;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LibroServiceAdmin implements IlibroServiceAdmin {

    private BoockRepository boockRepository;
    private CategoriaService catService;
    private IAdminAutors autorService;
    private autorRepository autorRepo;
    private formatoLibroRepository formatolIbroRepo;
    private idiomRepository idiomRepo;
    private tipolibroRepository tipoLibroRepo;
    private cloudinaryServices cloudinaryService;
    private IGeneroLibroService generoLibroService;
    private EmailService emailService;

    @Autowired
    public LibroServiceAdmin(BoockRepository boockRepository, CategoriaService catService, IAdminAutors autorService, autorRepository autorRepo, formatoLibroRepository formatolIbroRepo, idiomRepository idiomRepo, tipolibroRepository tipoLibroRepo, cloudinaryServices cloudinaryService, IGeneroLibroService generoLibroService, EmailService emailService) {
        this.boockRepository = boockRepository;
        this.catService = catService;
        this.autorService = autorService;
        this.autorRepo = autorRepo;
        this.formatolIbroRepo = formatolIbroRepo;
        this.idiomRepo = idiomRepo;
        this.tipoLibroRepo = tipoLibroRepo;
        this.cloudinaryService = cloudinaryService;
        this.generoLibroService = generoLibroService;
        this.emailService = emailService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boock create(
            LibroDtoReq libroDtoReq,
            MultipartFile portada,
            MultipartFile libro,
            MultipartFile fotoAutor,
            List<CategoriaAsignarDto> categorias,
            Integer id_autor,
            autorDtoReq autorDtoReq,
            List<GeneroDtoBasicReq> generos) {

        Optional<Boock> existelibro = boockRepository.existeLibroByNombre(libroDtoReq.getTitulo().trim());

        if (existelibro.isPresent()) {

            throw new DatoYaExistenteException("El libro ya existe en el sistema");
        }

        Boock boock = new Boock();

        boock.setTitulo(libroDtoReq.getTitulo().trim());

        if (libroDtoReq.getDescripcion() != null) {
            boock.setDescripcion(libroDtoReq.getDescripcion().trim());

        }
        boock.setEdicion(libroDtoReq.getEdicion());
        boock.setEditorial(libroDtoReq.getEditorial().trim());
        boock.setFechaPublicacion(libroDtoReq.getFechaPublicacion());
        boock.setIsbn(libroDtoReq.getIsbn().trim());
        boock.setPaisPublicacion(libroDtoReq.getPaisOrigen().trim());
        boock.setSubtitulo(libroDtoReq.getSubtitulo().trim());

        //relaciones
        boock.setFormatoLibro(formatolIbroRepo.findById(libroDtoReq.getId_formato_libro())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El formato del libro no existe en el sistema")));

        boock.setIdiom(idiomRepo.findById(libroDtoReq.getId_idioma())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El idiima no existe en el sistema")));

        boock.setTipoLibro(tipoLibroRepo.findById(libroDtoReq.getId_tipo_libro())
                .orElseThrow(() -> new DatoNoExistenteEcxeption("El tipo de libro no existe en el sistema")));

        CloudinaryUploadResponse cloudinaryUploadResponse = cloudinaryService.uploadPortadaLibro(portada, portada.getOriginalFilename());

        boock.setPortadaUrl(cloudinaryUploadResponse.getSecureUrl());
        boock.setPublicIdPortada(cloudinaryUploadResponse.getPublicId());

        CloudinaryUploadResponse response = cloudinaryService.uploadLibro(libro, libro.getOriginalFilename());

        boock.setPublicIdArchivo(response.getSecureUrl());
        boock.setPublicIdArchivo(response.getPublicId());

        AuditableUtils.create(boock, "prueba", "prueba");

        Boock libroSubido = boockRepository.save(boock);

        catService.asignarCategoriaLibro(libroSubido.getId(), categorias);

        if (id_autor != null) {

            Autor autor = autorRepo.findById(id_autor)
                    .orElseThrow(() -> new DatoNoExistenteEcxeption("El autor no existe en el sistema"));

            if (autor.isIsDelete()) {
                throw new DatoNoExistenteEcxeption("El autor no existe en el sistema");
            }

            boock.setAutor(autor);

        }

        if (autorDtoReq != null) {

            Autor autor = autorService.create(fotoAutor, autorDtoReq);

            boock.setAutor(autor);

        }

        if (generos != null) {

            generoLibroService.asignarGenerosToLibro(boock.getId(), generos);

        }

        emailService.CrearLibro(boock);

        return boock;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PageResponse<LibroAdminDtoResp> getAll(String nombre, Long id_lib, Boolean isDelete,
            Long excluyed_by_id, String nombre_autor, Integer id_autor, Integer id_cat,
            Integer id_genero, Integer id_idiom, Integer id_tipo_libro, Pageable pageable) {

        Page<LibroAdminDtoResp> page = boockRepository.getAllAdmin(nombre, id_lib, isDelete, excluyed_by_id,
                nombre_autor, id_autor, id_cat, id_genero, id_idiom, id_tipo_libro, pageable);

        if (page.isEmpty()) {
            throw new NoDatosQueMostrarExecption("No hay libros que mostrar");
        }

        return PageResponseUtils.CreatePageReponse(page);
    }

}
