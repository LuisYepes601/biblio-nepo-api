/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.cloudinary;

import com.nexus.biblioNepo.DTOS.response.Cloudinary.CloudinaryUploadResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author luis
 */
public interface ICloudinaryService {

    public CloudinaryUploadResponse uploadPrymaryPhotoBoock(MultipartFile file, String nameAutor, String namefile);
    
    public void deleteFile(String public_id);
}
