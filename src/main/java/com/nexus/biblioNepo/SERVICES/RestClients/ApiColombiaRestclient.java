/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.RestClients;

import com.nexus.biblioNepo.DTOS.response.City.CityDtoBasic;
import com.nexus.biblioNepo.DTOS.response.Departamentos.DepartamentoDtoResp;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.NoDatosQueMostrarExecption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ApiColombiaRestclient {

    private RestClient restClient;

    @Autowired
    public ApiColombiaRestclient(RestClient.Builder restClient) {
        this.restClient = restClient
                .baseUrl("https://api-colombia.com")
                .build();
    }

    public List<DepartamentoDtoResp> getDepartamentos() {

        ResponseEntity< List<DepartamentoDtoResp>> response = restClient.get()
                .uri("/api/v1/Department")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<DepartamentoDtoResp>>() {
                });

        if (response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {
            throw new NoDatosQueMostrarExecption("No hay departamentos que consumir de la api");
        }

        return response.getBody();
    }

    public List<CityDtoBasic> getCities() {

        ResponseEntity<List<CityDtoBasic>> response = restClient.
                get()
                .uri("api/v1/City")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<CityDtoBasic>>() {
                });

        if (response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) {

            throw new NoDatosQueMostrarExecption("No hay ciudades que consumir de la api");
        }

        return response.getBody();

    }

}
