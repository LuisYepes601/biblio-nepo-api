/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexus.biblioNepo.SERVICES.Templates;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 *
 * @author luis
 */
@Service
public class TemplateBuilder {

    @Autowired
    private SpringTemplateEngine templateEngine;

    public TemplateBuilder(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String createTemplateHtml(String template, Map<String, Object> variables) {

        Context context = new Context();

        context.setVariables(variables);

        return templateEngine.process(template, context);
    }
}
