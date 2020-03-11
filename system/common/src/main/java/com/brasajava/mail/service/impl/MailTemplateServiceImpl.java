package com.brasajava.mail.service.impl;

import java.util.Map;

import com.brasajava.mail.service.MailTemplateService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


public class MailTemplateServiceImpl implements MailTemplateService {

	private TemplateEngine templateEngine;

    public MailTemplateServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
 
    @Override
    public String build(Map<String, Object> params, String template) {

        Context context = new Context();
        
        for( Map.Entry<String,Object> entry: params.entrySet()) {
        	context.setVariable(entry.getKey(), entry.getValue());
        }
        
        return templateEngine.process(template, context);
    }

	
}
