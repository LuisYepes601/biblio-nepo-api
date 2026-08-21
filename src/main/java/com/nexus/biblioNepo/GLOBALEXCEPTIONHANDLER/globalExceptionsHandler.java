/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER;

import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.deleteFileCloudinary;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.uploadFileCloudinary;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author luis
 */
@RestControllerAdvice
public class globalExceptionsHandler {

    @ExceptionHandler(uploadFileCloudinary.class)
    public ResponseEntity<?> prueba(uploadFileCloudinary ex, HttpServletResponse response) {

        Map<String, String> error = new HashMap<>();

        StackTraceElement stackTraceElement = ex.getStackTrace()[0];

        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", String.valueOf(response.getStatus()));
        error.put("message", ex.getMessage());

        return ResponseEntity.ok(error);

    }

    @ExceptionHandler(deleteFileCloudinary.class)
    public ResponseEntity<Map<String, String>> deleteFileCloudinaryException(deleteFileCloudinary ex) {

        Map<String, String> error = new HashMap<>();

        error.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity
                .ok()
                .body(error);
    }

    @ExceptionHandler(DatoNoExistenteEcxeption.class)
    public ResponseEntity<?> handlerDatoNoExistenteEcxeption(DatoNoExistenteEcxeption ex) {

        Map<String, String> error = new HashMap<>();

        error.put("Error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);

    }
}
