/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.CONTROLLERS.Uptime;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author luis
 */
@RequestMapping(value = "/api/v1/life")
@RestController
public class Uptime {

    @GetMapping()
    public ResponseEntity<Map<String, String>> isLife() {

        return ResponseEntity
                .ok()
                .body(Map.of("message", "is live"));
    }

}
