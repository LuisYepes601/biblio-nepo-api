/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nexus.biblioNepo.DTOS.response.Cloudinary.CloudinaryDestroyFileResponse;
import com.nexus.biblioNepo.DTOS.response.Cloudinary.CloudinaryUploadResponse;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.deleteFileCloudinary;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.uploadFileCloudinary;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import restaurante_gratitude.demp.Helpers.Cloudinary.CloudinaryFileUtils;

/**
 *
 * @author luis
 */
@Service
public class cloudinaryServices implements ICloudinaryService {

    private Cloudinary cloudinary;

    @Autowired
    public cloudinaryServices(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public CloudinaryUploadResponse uploadPrymaryPhotoBoock(MultipartFile file, String nameAutor, String nameFile) {

        Map<String, String> response = new HashMap<>();

        try {

            response = cloudinary.uploader()
                    .uploadLarge(
                            file.getInputStream(),
                            CloudinaryFileUtils.ProfilePhtotoUser(nameAutor, nameFile));

        } catch (IOException iOException) {

            throw new uploadFileCloudinary("Error alsusbir el archivo a cloudinary");
        }

        CloudinaryUploadResponse cloudinaryUploadResponse = new CloudinaryUploadResponse();

        cloudinaryUploadResponse.setPublicId(response.get("public_id"));
        cloudinaryUploadResponse.setSecureUrl(response.get("secure_url"));

        return cloudinaryUploadResponse;
    }

    @Override
    public void deleteFile(String public_id) {
        if (public_id == null || public_id.isBlank()) {
            return; // nada que borrar
        }
        try {
            Map<String, Object> response = cloudinary
                    .uploader()
                    .destroy(public_id, ObjectUtils.emptyMap());
            String result = String.valueOf(response.get("result"));
            if (!"ok".equals(result) && !"not found".equals(result)) {
                throw new deleteFileCloudinary("Error al eliminar el archivo: " + result);
            }
        } catch (IOException e) {
            throw new deleteFileCloudinary("Error al eliminar el archivo");
        }
    }

    @Override
    public CloudinaryUploadResponse uploadFotoPerfil(MultipartFile file, String nameUser, String nameFile) {

        Map<String, Object> response = new HashMap<>();

        try {

            response = cloudinary.uploader()
                    .uploadLarge(file.getInputStream(), CloudinaryFileUtils.ProfilePhtotoUser(nameUser, nameFile));

        } catch (IOException e) {
        }

        CloudinaryUploadResponse cloudinaryUploadResponse = new CloudinaryUploadResponse();

        cloudinaryUploadResponse.setPublicId(response.get("public_id").toString());
        cloudinaryUploadResponse.setSecureUrl(response.get("secure_url").toString());

        return cloudinaryUploadResponse;
    }

    @Override
    public CloudinaryUploadResponse uploadPortadaLibro(MultipartFile portada, String nameFile) {

        try {

            Map<String, Object> response = cloudinary.uploader()
                    .uploadLarge(
                            portada.getInputStream(),
                            CloudinaryFileUtils.portadaLibro(nameFile)
                    );

            System.out.println("RESPUESTA CLOUDINARY: " + response);

            Object publicId = response.get("public_id");
            Object secureUrl = response.get("secure_url");

            if (publicId == null) {
                throw new RuntimeException(
                        "Cloudinary no devolvió public_id. Respuesta: " + response
                );
            }

            if (secureUrl == null) {
                throw new RuntimeException(
                        "Cloudinary no devolvió secure_url. Respuesta: " + response
                );
            }

            CloudinaryUploadResponse cloudinaryUploadResponse
                    = new CloudinaryUploadResponse();

            cloudinaryUploadResponse.setPublicId(publicId.toString());
            cloudinaryUploadResponse.setSecureUrl(secureUrl.toString());

            return cloudinaryUploadResponse;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error al subir la portada a Cloudinary",
                    e
            );
        }
    }

    @Override
    public CloudinaryUploadResponse uploadLibro(MultipartFile libro, String nameFile) {

        try {

            Map<String, Object> response = cloudinary.uploader()
                    .uploadLarge(
                            libro.getInputStream(),
                            CloudinaryFileUtils.libro(nameFile)
                    );

            System.out.println("RESPUESTA CLOUDINARY: " + response);

            Object publicId = response.get("public_id");
            Object secureUrl = response.get("secure_url");

            if (publicId == null || secureUrl == null) {
                throw new RuntimeException(
                        "Cloudinary no devolvió public_id o secure_url. Respuesta: "
                        + response
                );
            }

            CloudinaryUploadResponse cloudinaryUploadResponse
                    = new CloudinaryUploadResponse();

            cloudinaryUploadResponse.setPublicId(publicId.toString());
            cloudinaryUploadResponse.setSecureUrl(secureUrl.toString());

            return cloudinaryUploadResponse;

        } catch (IOException e) {

            throw new RuntimeException(
                    "Error al subir el libro a Cloudinary",
                    e
            );
        }
    }
}
