/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.RestCountriesClient;

import com.nexus.biblioNepo.DTOS.response.Restcountries.Data;
import com.nexus.biblioNepo.DTOS.response.Restcountries.PaisResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 *
 * @author luis
 */
@Service
public class RescountriesService {

    private RestClient restClient;
    private String token;

    @Autowired
    public RescountriesService(RestClient.Builder restClient) {
        this.restClient = restClient
                .baseUrl("https://api.restcountries.com")
                .build();
        this.token = "rc_live_c871047494f74d65be385d9f959a533e";
    }

    public PaisResponse getPais() {

        return restClient
                .get()
                .uri("/countries/v5/codes.alpha_3/COL")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PROBLEM_JSON_VALUE)
                .retrieve()
                .body(PaisResponse.class);
    }

}
