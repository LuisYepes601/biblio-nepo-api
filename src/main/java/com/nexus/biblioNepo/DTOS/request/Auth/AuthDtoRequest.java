/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.Auth;

/**
 *
 * @author luis
 */
public class AuthDtoRequest {
    
    private String email;
    private String password;

    public AuthDtoRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthDtoRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
