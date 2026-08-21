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

        try {

            Map<String, Object> response = cloudinary.
                    uploader()
                    .destroy(public_id,
                            ObjectUtils.emptyMap());

            if (!"ok".equals(response.get("result").toString())) {

                throw new deleteFileCloudinary("Error al eliminar el archivo");
            }

        } catch (IOException e) {

            throw new deleteFileCloudinary("Error al eliminar el archivo");
        }

    }

}
