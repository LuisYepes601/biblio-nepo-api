/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Email;

import com.nexus.biblioNepo.DTOS.request.EmailDinamico.BrevoRecipientDto;
import com.nexus.biblioNepo.DTOS.request.EmailDinamico.BrevoSenderDto;
import com.nexus.biblioNepo.DTOS.request.EmailDinamico.EmailDinamicoDto;
import com.nexus.biblioNepo.ENTYTIES.Boock;
import com.nexus.biblioNepo.ENTYTIES.usuario;
import com.nexus.biblioNepo.GLOBALEXCEPTIONHANDLER.exceptions.DatoNoExistenteEcxeption;
import com.nexus.biblioNepo.REPOSITORIES.usuarioRepository;
import com.nexus.biblioNepo.SERVICES.RestClients.BrevRestClient;
import com.nexus.biblioNepo.SERVICES.Templates.TemplateBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author luis
 */
@Service
public class EmailService {

    private BrevRestClient brevRestClient;
    private TemplateBuilder templateBuilder;
    private usuarioRepository userRepo;

    @Autowired
    public EmailService(BrevRestClient brevRestClient, TemplateBuilder templateBuilder, usuarioRepository userRepo) {
        this.brevRestClient = brevRestClient;
        this.templateBuilder = templateBuilder;
        this.userRepo = userRepo;
    }

    public void SendEmailRegistro(usuario user) {

        Map<String, Object> variables = new HashMap<>();
        variables.put("usuario", user);
        variables.put("urlSistema", "h");

        String htmlContent = templateBuilder.createTemplateHtml("Registro", variables);

        EmailDinamicoDto emailDinamicoDto = new EmailDinamicoDto();

        List<usuario> usuaios = new ArrayList<>();

        usuaios.add(user);

        emailDinamicoDto.setHtmlContent(htmlContent);

        BrevoSenderDto brevoSenderDto = new BrevoSenderDto();
        brevoSenderDto.setEmail("lyepesm@unicartagena.edu.co");
        brevoSenderDto.setName("BiblioNepo");

        emailDinamicoDto.setSender(brevoSenderDto);

        emailDinamicoDto.setSubject("Bienvenido a BiblioNepo");

        List<BrevoRecipientDto> brevoRecipientDtos = new ArrayList<>();
        BrevoRecipientDto brevoRecipientDto = new BrevoRecipientDto();

        brevoRecipientDto.setEmail(user.getEmail());
        brevoRecipientDto.setName("Pueba");

        brevoRecipientDtos.add(brevoRecipientDto);

        emailDinamicoDto.setTo(brevoRecipientDtos);

        brevRestClient.enviarDinamico(emailDinamicoDto);

    }

    public void RecuperarcontraseñaEmail(usuario usuario, String newPassword) {

        Map<String, Object> variables = new HashMap<>();

        variables.put("usuario", usuario);
        variables.put("passwordTemporal", newPassword);

        String html = templateBuilder.createTemplateHtml("recuperarPassword", variables);

        EmailDinamicoDto emailDinamicoDto = new EmailDinamicoDto();

        emailDinamicoDto.setHtmlContent(html);

        BrevoSenderDto brevoSenderDto = new BrevoSenderDto();
        brevoSenderDto.setEmail("lyepesm@unicartagena.edu.co");
        brevoSenderDto.setName("BiblioNepo");

        emailDinamicoDto.setSender(brevoSenderDto);

        emailDinamicoDto.setSubject("Recuperación de Contraseña");

        List<BrevoRecipientDto> brevoRecipientDtos = new ArrayList<>();
        BrevoRecipientDto brevoRecipientDto = new BrevoRecipientDto();

        brevoRecipientDto.setEmail(usuario.getEmail());
        brevoRecipientDto.setName("Pueba");

        brevoRecipientDtos.add(brevoRecipientDto);

        emailDinamicoDto.setTo(brevoRecipientDtos);

        brevRestClient.enviarDinamico(emailDinamicoDto);
    }

    public void CrearLibro(Boock boock) {

      

        Map<String, Object> variables = new HashMap<>();

        variables.put("nombreUsuario", "LUS YEPES");
        variables.put("urlPortada", boock.getPortadaUrl());
        variables.put("tituloLibro", boock.getTitulo());
        variables.put("subtitulo", boock.getSubtitulo());
        variables.put("isbn", boock.getIsbn());
        variables.put("editorial", boock.getEditorial());
        variables.put("fechaPublicacion", boock.getFechaPublicacion());
        variables.put("edicion", boock.getEdicion());
        variables.put("idLibro", boock.getId());

        if (boock.getDescripcion() != null) {
            variables.put("descripcion", boock.getDescripcion());

        }

        String html = templateBuilder.createTemplateHtml("libroCreado", variables);

        EmailDinamicoDto emailDinamicoDto = new EmailDinamicoDto();

        emailDinamicoDto.setHtmlContent(html);

        emailDinamicoDto.setSubject("Registro de libro");

        BrevoSenderDto brevoSenderDto = new BrevoSenderDto();
        brevoSenderDto.setEmail("lyepesm@unicartagena.edu.co");
        brevoSenderDto.setName("BiblioNepo");

        emailDinamicoDto.setSender(brevoSenderDto);

        List<BrevoRecipientDto> brevoRecipientDtos = new ArrayList<>();

        BrevoRecipientDto brevoRecipientDto = new BrevoRecipientDto();
        brevoRecipientDto.setEmail("yepesluis006@gmail.com");
        brevoRecipientDto.setName("LUIS");

        brevoRecipientDtos.add(brevoRecipientDto);

        emailDinamicoDto.setTo(brevoRecipientDtos);
        
        brevRestClient.enviarDinamico(emailDinamicoDto);
    }
}
