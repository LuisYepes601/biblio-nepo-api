/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Auth;

import com.nexus.biblioNepo.DTOS.request.Auth.AuthDtoRequest;
import com.nexus.biblioNepo.DTOS.response.Auth.AuthResponseDto;

/**
 *
 * @author luis
 */
public interface IAuthService {
    
    public AuthResponseDto autenticarse(AuthDtoRequest authDtoRequest);
}
