/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.PalabrasClave;

import com.nexus.biblioNepo.DTOS.request.PalabraClaveDtoReq;
import com.nexus.biblioNepo.DTOS.response.PageResponse;
import com.nexus.biblioNepo.DTOS.response.PalabrasClaves.PalabraClaveDtoResp;
import com.nexus.biblioNepo.ENTYTIES.palabra_clave;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luis
 */
public interface IPalabrasClave {

    public void createByIdLibro(List<PalabraClaveDtoReq> palabras_clave, Long id_libro);

    public PageResponse<PalabraClaveDtoResp> getPalabrasClavesbyIdLibro(Long id_libro, Pageable pageable);

    public void editarPalabraClave(Integer id_palabra, Long id_libro, PalabraClaveDtoReq palabraClaveDtoReq);

}
