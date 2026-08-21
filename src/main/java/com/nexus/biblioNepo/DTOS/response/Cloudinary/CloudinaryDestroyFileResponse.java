/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.response.Cloudinary;

/**
 *
 * @author luis
 */
public class CloudinaryDestroyFileResponse {
    
    private String status;

    public CloudinaryDestroyFileResponse(String status) {
        this.status = status;
    }

    public CloudinaryDestroyFileResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
