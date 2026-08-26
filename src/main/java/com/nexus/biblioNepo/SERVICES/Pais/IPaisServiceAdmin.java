/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Pais;

import com.nexus.biblioNepo.DTOS.request.Pais.PaisDtoReq;
import com.nexus.biblioNepo.ENTYTIES.Pais;

/**
 *
 * @author luis
 */
public interface IPaisServiceAdmin {

    public Pais create(PaisDtoReq paisDtoReq);
}
