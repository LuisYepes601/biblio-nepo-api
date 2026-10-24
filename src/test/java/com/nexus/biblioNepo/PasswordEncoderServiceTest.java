/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author luis
 */
public class PasswordEncoderServiceTest {

    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
      
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    @Test
    @DisplayName("CP-UNI-01.1: Generación correcta de Hash BCrypt con prefijo $2a$10$")
    void testEncodePasswordSuccess() {
        String rawPassword = "AdminPass2026*";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        assertNotNull(encodedPassword, "El hash no debe ser nulo");
        assertTrue(encodedPassword.startsWith("$2a$10$"), "El hash debe tener el factor de costo 10");
        assertEquals(60, encodedPassword.length(), "El hash BCrypt debe contener exactamente 60 caracteres");
    }

    @Test
    @DisplayName("CP-UNI-01.2: Verificación de coincidencia entre texto plano y hash")
    void testMatchesPasswordSuccess() {
        String rawPassword = "AdminPass2026*";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword),
                "Debe validar positivamente la contraseña correcta");
        assertFalse(passwordEncoder.matches("PasswordErrada123", encodedPassword),
                "Debe rechazar una contraseña incorrecta");
    }
}
