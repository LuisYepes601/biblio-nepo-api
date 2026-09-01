/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.RestClients;

import com.nexus.biblioNepo.DTOS.request.EmailDinamico.EmailDinamicoDto;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 *
 * @author luis
 */
@Service
public class BrevRestClient {

    private RestClient restClient;

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Autowired
    public BrevRestClient(
            RestClient.Builder builder,
            @Value("${brevo.api.key}") String brevoApiKey) {

        this.restClient = builder
                .baseUrl("https://api.brevo.com/v3")
                .defaultHeader("api-key", brevoApiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public void enviarDinamico(EmailDinamicoDto emailDinamicoDto) {

        ResponseEntity<Map> response = restClient.
                post()
                .uri("/smtp/email")
                .body(emailDinamicoDto)
                .retrieve()
                .toEntity(Map.class);

    }

}
