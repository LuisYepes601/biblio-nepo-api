/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Prestamos;

import com.nexus.biblioNepo.DTOS.request.Prestamos.PrestamoDtoreq;

/**
 *
 * @author luis
 */
public interface IPrestamosService {
    
    public void prestarLibro(PrestamoDtoreq prestamoDtoreq);
}
