/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.DTOS.request.EmailDinamico;

import java.util.List;

/**
 *
 * @author luis
 */
public class EmailDinamicoDto {

    private BrevoSenderDto sender;

    private List<BrevoRecipientDto> to;

    private String subject;

    private String htmlContent;

    public EmailDinamicoDto(BrevoSenderDto sender, List<BrevoRecipientDto> to, String subject, String htmlContent) {
        this.sender = sender;
        this.to = to;
        this.subject = subject;
        this.htmlContent = htmlContent;
    }

    public EmailDinamicoDto() {
    }

    public BrevoSenderDto getSender() {
        return sender;
    }

    public void setSender(BrevoSenderDto sender) {
        this.sender = sender;
    }

    public List<BrevoRecipientDto> getTo() {
        return to;
    }

    public void setTo(List<BrevoRecipientDto> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
    
    
}
