/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.RestcountriesDev;

import com.nexus.biblioNepo.DTOS.request.Idiomas.IdiomaDtoReq;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 *
 * @author luis
 */
@Service
public class RestcountriesDev {

    private RestClient restClient;

    public RestcountriesDev(RestClient.Builder restClient) {
        this.restClient = restClient
                .baseUrl("https://countries.dev")
                .build();
    }

    public List<IdiomaDtoReq> getIdioms() {

        ResponseEntity<List<IdiomaDtoReq>> response = restClient.get()
                .uri("/languages")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<IdiomaDtoReq>>() {
                });

        if (response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {

            throw new NoDatosQueMostrarExecption("La api de idiomas no encuentra el servicio");
        }

        return response.getBody();

    }
}
