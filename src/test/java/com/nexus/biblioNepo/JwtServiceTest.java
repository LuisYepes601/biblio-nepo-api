package com.nexus.biblioNepo;

import com.nexus.biblioNepo.SERVICES.JwtService.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    // Clave secreta de prueba (mínimo 256 bits para HMAC-SHA)
    private final String testSecret = "VN0qYleKGvdU4qwvwtAmzMjaNi7R020DohTx362lidM_TestKey2026";

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        // Inyecta el atributo @Value("${jwt.secret}") directamente en tu clase JwtService
        ReflectionTestUtils.setField(jwtService, "secretKey", testSecret);
    }

    @Test
    @DisplayName("CP-UNI-02.1: Generación de JWT y extracción de Username (Correo)")
    void testGenerateTokenAndExtractUsername() {
        // 1. Datos de usuario e ID
        UserDetails userDetails = new User(
                "lyepes@unicartagena.edu.co",
                "AdminPass2026*",
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
        Long idUser = 1L;

        // 2. Generación del Token con (UserDetails, Long)
        String token = jwtService.generateToken(userDetails, idUser);

        // 3. Verificaciones de Estructura e Integridad (Header.Payload.Signature)
        assertNotNull(token, "El token generado no debe ser nulo");
        assertEquals(3, token.split("\\.").length, "El JWT debe constar de 3 partes separadas por puntos");

        // 4. Extraer el Username
        String extractedUsername = jwtService.extractUsername(token);
        assertEquals("lyepes@unicartagena.edu.co", extractedUsername, "El correo extraído debe coincidir con el usuario");
    }

    @Test
    @DisplayName("CP-UNI-02.2: Validación de vigencia y firma del Token JWT")
    void testIsTokenValidSuccess() {
        UserDetails userDetails = new User(
                "lyepes@unicartagena.edu.co",
                "AdminPass2026*",
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
        Long idUser = 1L;

        // 1. Generar token
        String token = jwtService.generateToken(userDetails, idUser);

        // 2. Probar el método público isTokenValid de tu JwtService
        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid, "El token recién emitido debe ser plenamente válido para el usuario");
    }
}