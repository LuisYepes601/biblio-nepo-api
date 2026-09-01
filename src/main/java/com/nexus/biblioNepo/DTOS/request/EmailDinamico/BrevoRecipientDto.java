/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.EmailDinamico;

/**
 *
 * @author luis
 */
public class BrevoRecipientDto {
    
    private String email;
    
    private String name;

    public BrevoRecipientDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public BrevoRecipientDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
