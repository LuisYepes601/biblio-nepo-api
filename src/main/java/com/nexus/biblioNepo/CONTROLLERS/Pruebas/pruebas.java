/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Pruebas;

import com.nexus.biblioNepo.DTOS.response.Restcountries.Data;
import com.nexus.biblioNepo.DTOS.response.Restcountries.PaisResponse;
import com.nexus.biblioNepo.SERVICES.RestCountriesClient.RescountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luis
 */
@RequestMapping(value = "/api/v1/pruebas")
@RestController
public class pruebas {

    private RescountriesService rescountriesService;

    @Autowired
    public pruebas(RescountriesService rescountriesService) {
        this.rescountriesService = rescountriesService;
    }

    @GetMapping()
    public ResponseEntity<PaisResponse> get() {

        return ResponseEntity.
                ok()
                .body(rescountriesService.getPais());
    }

}
